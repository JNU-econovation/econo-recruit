package com.econovation.recruitdomain.domains.email_template.domain;

import com.econovation.recruitcommon.annotation.EnumClass;
import com.econovation.recruitdomain.domains.applicant.domain.state.PassStates;

@EnumClass
public enum EmailTemplateType {
    // 서류합격
    FIRST_PASSED("서류 합격", "first_passed", PassStates.FIRST_PASSED),
    // 서류불합격
    FIRST_FAILED("서류 불합격", "first_failed", PassStates.FIRST_FAILED),
    // 면접합격
    FINAL_PASSED("면접 합격", "final_passed", PassStates.FINAL_PASSED),
    // 면접불합격
    FINAL_FAILED("면접 불합격", "final_failed", PassStates.FINAL_FAILED),
    ;
    private String name;
    private String type;
    private PassStates passState;

    EmailTemplateType(String name, String type, PassStates passStates) {
        this.name = name;
        this.type = type;
        this.passState = passStates;
    }


}
