package com.econovation.recruit.api.email_template.util;

import com.econovation.recruitdomain.domains.applicant.domain.MongoAnswer;
import com.econovation.recruitdomain.domains.applicant.domain.state.PassStates;
import java.util.Arrays;
import lombok.Getter;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Getter
public enum DefaultTemplate {

    FIRST_PASSED(PassStates.FIRST_PASSED,
            "에코노베이션 신입 모집 서류 결과 안내",
            "email-first-passed"
    ),
    FINAL_PASSED(
            PassStates.FINAL_PASSED,
            "에코노베이션 신입 모집 최종 결과 안내",
            "email-final-passed"
    ),
    FIRST_FAILED(
            PassStates.FIRST_FAILED,
            "에코노베이션 신입 모집 서류 결과 안내",
            "email-first-failed"
    ),
    FINAL_FAILED(
            PassStates.FINAL_FAILED,
            "에코노베이션 신입 모집 최종 결과 안내",
            "email-final-failed"
    );

    private PassStates passStates;
    private String subject;
    private String templateName;

    DefaultTemplate(PassStates passStates, String subject, String templateName) {
        this.passStates = passStates;
        this.subject = subject;
        this.templateName = templateName;
    }

    public static DefaultTemplate getTemplate(PassStates passStates) {
        return Arrays.stream(DefaultTemplate.values())
                .filter(template -> template.getPassStates().equals(passStates))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 상태 처리"));
    }

    public String contextApply(MongoAnswer applicant){
        TemplateEngine templateEngine = new TemplateEngine();
        Context context = new Context();

        context.setVariable("name", applicant.getQna().get("name").toString());
        context.setVariable("year", applicant.getYear());

        return templateEngine.process(templateName, context);
    }


}
