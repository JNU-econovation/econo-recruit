package com.econovation.recruit.api.email.service;

import com.econovation.recruit.api.email_template.util.DefaultEmailTemplateGenerator;
import com.econovation.recruitdomain.domains.applicant.domain.MongoAnswer;
import com.econovation.recruitinfrastructure.apache.CommonsEmailSender;
import java.io.File;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApplicantEmailService {

    private final CommonsEmailSender emailSender;
    private final DefaultEmailTemplateGenerator templateGenerator;

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
