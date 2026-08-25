package com.econovation.recruitdomain.domains.email_template.domain;

import com.econovation.recruitdomain.domains.applicant.constant.ApplicantQnaKeys;
import com.econovation.recruitdomain.domains.applicant.domain.MongoAnswer;
import com.econovation.recruitdomain.domains.applicant.domain.state.PassStates;
import com.econovation.recruitdomain.domains.email_template.exception.EmailTemplateNotFoundException;
import java.util.Arrays;
import lombok.Getter;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Getter
public enum DefaultEmailTemplate {
    FIRST_PASSED(PassStates.FIRST_PASSED, "에코노베이션 신입 모집 서류 결과 안내", "email-first-passed"),
    FINAL_PASSED(PassStates.FINAL_PASSED, "에코노베이션 신입 모집 최종 결과 안내", "email-final-passed"),
    FIRST_FAILED(PassStates.FIRST_FAILED, "에코노베이션 신입 모집 서류 결과 안내", "email-first-failed"),
    FINAL_FAILED(PassStates.FINAL_FAILED, "에코노베이션 신입 모집 최종 결과 안내", "email-final-failed");

    private PassStates passStates;
    private String subject;
    private String templateName;

    DefaultEmailTemplate(PassStates passStates, String subject, String templateName) {
        this.passStates = passStates;
        this.subject = subject;
        this.templateName = templateName;
    }

    public static DefaultEmailTemplate getTemplate(PassStates passStates) {
        return Arrays.stream(DefaultEmailTemplate.values())
                .filter(template -> template.getPassStates().equals(passStates))
                .findFirst()
                .orElseThrow(() -> EmailTemplateNotFoundException.EXCEPTION);
    }

    public String contextApply(MongoAnswer applicant) {
        TemplateEngine templateEngine = new TemplateEngine();
        Context context = new Context();

        context.setVariable("name", applicant.getQna().get(ApplicantQnaKeys.NAME).toString());
        context.setVariable("year", applicant.getYear());

        return templateEngine.process(templateName, context);
    }
}
