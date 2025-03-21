package com.econovation.recruit.api.email.service;

import com.econovation.recruitdomain.domains.email_template.domain.DefaultEmailTemplate;
import com.econovation.recruitdomain.domains.applicant.domain.MongoAnswer;
import com.econovation.recruitdomain.domains.applicant.domain.state.PassStates;
import com.econovation.recruitinfrastructure.apache.CommonsEmailSender;
import java.io.File;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApplicantEmailService {

    private final CommonsEmailSender emailSender;

    private String openChatUrl;

    private LocalDateTime urlDeadLine;

    @Value("${econovation.file.path.portfolio}")
    private String filePath;

    public boolean sendEmail(MongoAnswer applicant) {
        String template = generateEmailTemplate(applicant);
        String subject = generateEmailSubject(applicant);
        File attachment = getPortfolioFile(applicant);
        String email = applicant.getQna().get("email").toString();

        if(Objects.isNull(attachment))
            return emailSender.sendEmail(email, subject, template);
        else
            if(attachment.exists())
                return emailSender.sendEmailWithAttachment(email, subject, template, attachment);
            else {
                log.error("attachment 가 첨부되지 않았습니다. file dir : {}", attachment.getAbsolutePath());
                return emailSender.sendEmail(email, subject, template);
            }

    }


    private String generateEmailTemplate(MongoAnswer applicant) {
        PassStates passState = applicant.getApplicantState().getPassStateToEnum();
        return DefaultEmailTemplate.getTemplate(passState).contextApply(applicant);
    }

    private String generateEmailSubject(MongoAnswer applicant){
        PassStates passState = applicant.getApplicantState().getPassStateToEnum();
        return DefaultEmailTemplate.getTemplate(passState).getSubject();
    }

    private File getPortfolioFile(MongoAnswer applicant) {
        // applicant에서 포트폴리오 파일 경로나 ID 등을 이용해 파일을 가져오는 로직
        PassStates passState = applicant.getApplicantState().getPassStateToEnum();
        if(passState==PassStates.FINAL_PASSED)
            return new File(filePath);
        return null;
    }

}
