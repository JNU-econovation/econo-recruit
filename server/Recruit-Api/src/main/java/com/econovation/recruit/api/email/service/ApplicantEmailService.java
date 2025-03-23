package com.econovation.recruit.api.email.service;

import com.econovation.recruit.api.applicant.usecase.ApplicantQueryUseCase;
import com.econovation.recruit.api.email_template.util.DefaultEmailTemplateGenerator;
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
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${econovation.year}")
    private int year;

    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void sendEmail(int year, String state) {
        PassStates target = PassStates.findStatus(state);
        List<MongoAnswer> applicants = applicantQueryUseCase.getApplicantsByYear(year)
                .stream().filter(applicant -> applicant.getApplicantState().getPassStateToEnum() == target)
                .toList();

        for (MongoAnswer applicant : applicants) {
            boolean result = sendEmail(applicant);
            if(!result) log.error("Email 발송 실패 : {}", applicant.getId());
            else{
                String applicantId = applicant.getId();
                String passState = applicant.getApplicantState().getPassStateToEnum().name();

                Events.raise(EmailSendEvent.of(applicantId, passState, ""));
            }
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
}
