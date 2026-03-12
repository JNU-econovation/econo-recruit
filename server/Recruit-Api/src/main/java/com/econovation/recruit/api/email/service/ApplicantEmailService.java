package com.econovation.recruit.api.email.service;

import com.econovation.recruit.api.applicant.usecase.ApplicantQueryUseCase;
import com.econovation.recruit.api.email_template.util.DefaultEmailTemplateGenerator;
import com.econovation.recruit.api.recruitment.util.LatestRecruitmentVo;
import com.econovation.recruitdomain.common.aop.domainEvent.Events;
import com.econovation.recruitdomain.domains.applicant.domain.MongoAnswer;
import com.econovation.recruitdomain.domains.applicant.domain.state.PassStates;
import com.econovation.recruitdomain.domains.email_template.event.EmailSendEvent;
import com.econovation.recruitinfrastructure.apache.CommonsEmailSender;
import java.io.File;
import java.util.List;
import java.util.Objects;
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

    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void sendEmail(int year, String state, boolean slackNotify, String slackUrl) {
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
                Events.raise(toEmailSendEvent(applicant, slackNotify, slackUrl));
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void sendEmail(String applicantId, boolean slackNotify, String slackUrl) {
        MongoAnswer applicant = applicantQueryUseCase.getApplicantById(applicantId);
        if (sendEmail(applicant)) {
            Events.raise(toEmailSendEvent(applicant, slackNotify, slackUrl));
        }
    }

    public boolean sendEmail(MongoAnswer applicant) {
        String template = templateGenerator.generateEmailTemplate(applicant);
        String subject = templateGenerator.generateSubject(applicant);
        File attachment = templateGenerator.getPortfolioFile(applicant);
        String email = applicant.getQna().get("email").toString();

        if (Objects.isNull(attachment)) return emailSender.sendEmail(email, subject, template);
        else if (attachment.exists())
            return emailSender.sendEmailWithAttachment(email, subject, template, attachment);
        else {
            log.error("attachment 가 첨부되지 않았습니다. file dir : {}", attachment.getAbsolutePath());
            return emailSender.sendEmail(email, subject, template);
        }
    }

    private EmailSendEvent toEmailSendEvent(
            MongoAnswer applicant, boolean slackNotify, String slackUrl) {
        String passState = applicant.getApplicantState().getPassStateToEnum().name();
        String name = applicant.getQna().getOrDefault("name", "").toString();
        String field1 = applicant.getQna().getOrDefault("field1", "").toString();
        String field2 = applicant.getQna().getOrDefault("field2", "").toString();

        return EmailSendEvent.of(
                applicant.getId(), passState, slackNotify, slackUrl, name, field1, field2);
    }
}
