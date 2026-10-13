package com.econovation.recruit.api.email.service;

import com.econovation.recruit.api.applicant.usecase.ApplicantQueryUseCase;
import com.econovation.recruit.api.email_template.util.DefaultEmailTemplateGenerator;
import com.econovation.recruit.api.recruitment.util.LatestRecruitmentVo;
import com.econovation.recruitdomain.common.aop.domainEvent.Events;
import com.econovation.recruitdomain.domains.applicant.domain.MongoAnswer;
import com.econovation.recruitdomain.domains.applicant.domain.state.PassStates;
import com.econovation.recruitdomain.domains.email_template.event.EmailSendEvent;
import com.econovation.recruitinfrastructure.apache.CommonsEmailSender;
import com.econovation.recruitinfrastructure.slack.SlackMessageProvider;
import com.econovation.recruitinfrastructure.slack.config.SlackProperties;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApplicantEmailService {

    private final CommonsEmailSender emailSender;
    private final ApplicantQueryUseCase applicantQueryUseCase;
    private final DefaultEmailTemplateGenerator templateGenerator;
    private final LatestRecruitmentVo latestRecruitInfo;
    private final SlackMessageProvider slackMessageProvider;
    private final SlackProperties slackProperties;

    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void sendEmail(int year, String state) {
        PassStates target = PassStates.findStatus(state);
        List<MongoAnswer> applicants =
                applicantQueryUseCase.getApplicantsByYear(year).stream()
                        .filter(
                                applicant ->
                                        applicant.getApplicantState().getPassStateToEnum()
                                                == target)
                        .toList();

        for (MongoAnswer applicant : applicants) {
            boolean result = sendEmail(applicant);
            if (!result) log.error("Email 발송 실패 : {}", applicant.getId());
            else {
                String applicantId = applicant.getId();
                String passState = applicant.getApplicantState().getPassStateToEnum().name();

                Events.raise(EmailSendEvent.of(applicantId, passState, ""));
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void sendEmail(String applicantId) {
        MongoAnswer applicant = applicantQueryUseCase.getApplicantById(applicantId);
        if (sendEmail(applicant)) {
            String passState = applicant.getApplicantState().getPassStateToEnum().name();
            Events.raise(EmailSendEvent.of(applicantId, passState, ""));
        }
    }

    public boolean sendEmail(MongoAnswer applicant) {
        String template = templateGenerator.generateEmailTemplate(applicant);
        String subject = templateGenerator.generateSubject(applicant);
        File attachment = templateGenerator.getPortfolioFile(applicant);
        String email = applicant.getQna().get("email").toString();

        boolean result;
        if (Objects.isNull(attachment)) result = emailSender.sendEmail(email, subject, template);
        else if (attachment.exists())
            result = emailSender.sendEmailWithAttachment(email, subject, template, attachment);
        else {
            log.error("attachment 가 첨부되지 않았습니다. file dir : {}", attachment.getAbsolutePath());
            result = emailSender.sendEmail(email, subject, template);
        }

        if (result) {
            slackMessageProvider.sendMessage(
                    slackProperties.getUrl(), generateNotificationMessage(applicant));
        }
        return result;
    }

    private String generateNotificationMessage(MongoAnswer applicant) {
        Map<String, Object> qna = applicant.getQna();
        String fields =
                Stream.of(getValue(qna, "field1"), getValue(qna, "field2"))
                        .filter(value -> !value.isBlank())
                        .distinct()
                        .collect(Collectors.joining(" / "));
        if (fields.isBlank()) fields = getValue(qna, "field");

        return String.format(
                """
                [메일 발송 성공]
                - 이름 : %s
                - 지원 분야 : %s
                - 합격 상태 : %s
                """,
                getValue(qna, "name"),
                fields,
                getStateName(applicant.getApplicantState().getPassStateToEnum()));
    }

    private String getValue(Map<String, Object> qna, String key) {
        if (qna == null) return "";
        return Objects.toString(qna.get(key), "");
    }

    private String getStateName(PassStates state) {
        return switch (state) {
            case NON_PROCESSED -> "미처리";
            case FIRST_PASSED -> "1차 합격";
            case FIRST_FAILED -> "1차 불합격";
            case FINAL_PASSED -> "최종 합격";
            case FINAL_FAILED -> "최종 불합격";
        };
    }
}
