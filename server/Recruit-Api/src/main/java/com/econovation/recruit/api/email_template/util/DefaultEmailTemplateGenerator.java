package com.econovation.recruit.api.email_template.util;

import com.econovation.recruitdomain.domains.applicant.domain.MongoAnswer;
import com.econovation.recruitdomain.domains.applicant.domain.state.PassStates;
import com.econovation.recruitdomain.domains.email_template.domain.DefaultEmailTemplate;
import java.io.File;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Slf4j
@Component
public class DefaultEmailTemplateGenerator {

    private final TemplateEngine templateEngine;
    private final DefaultEmailProperties emailProperties;
    private final File portfolioFile;

    public DefaultEmailTemplateGenerator(TemplateEngine templateEngine,
                                         DefaultEmailProperties emailProperties) {
        this.templateEngine = templateEngine;
        this.emailProperties = emailProperties;
        this.portfolioFile = new File(emailProperties.getFilePath());
    }

    public File getPortfolioFile(MongoAnswer applicant) {
        // applicant에서 포트폴리오 파일 경로나 ID 등을 이용해 파일을 가져오는 로직
        PassStates passState = applicant.getApplicantState().getPassStateToEnum();
        if(passState==PassStates.FINAL_PASSED)
            return portfolioFile;
        return null;
    }

    public String generateSubject(MongoAnswer applicant) {
        PassStates passState = applicant.getApplicantState().getPassStateToEnum();
        return DefaultEmailTemplate.getTemplate(passState).getSubject();
    }

    public String generateEmailTemplate(MongoAnswer applicant) {
        return contextApply(applicant);
    }

    private  String contextApply(MongoAnswer applicant){
        PassStates passState = applicant.getApplicantState().getPassStateToEnum();
        String templateName = DefaultEmailTemplate.getTemplate(passState).getTemplateName();
        Context context = new Context();

        commonContext(applicant, context);

        switch(passState){
            case FIRST_PASSED -> {
                context.setVariable("deadline", emailProperties.getOpenChatUrlDeadLine());
                context.setVariable("link", emailProperties.getOpenChatUrl());
                return templateEngine.process(templateName, context);
            }

            case FINAL_PASSED -> {
                context.setVariable("datetime", emailProperties.getOtSchedule());
                context.setVariable("place", emailProperties.getOtPlace());
                return templateEngine.process(templateName, context);
            }
        }

        log.error("상태에 맞는 템플릿을 찾을 수 없음 : {}", passState);
        return "";
    }

    private void commonContext(MongoAnswer applicant, Context context) {
        context.setVariable("name", applicant.getQna().get("name").toString());
        context.setVariable("year", applicant.getYear());
    }

}
