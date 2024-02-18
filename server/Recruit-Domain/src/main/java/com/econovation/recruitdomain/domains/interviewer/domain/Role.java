package com.econovation.recruitdomain.domains.interviewer.domain;

import com.econovation.recruitdomain.domains.interviewer.exception.InterviewerInvalidRoleException;
import lombok.Getter;

@Getter
public enum Role {
    ROLE_PRESIDENT("PRESIDENT"), // 회장단
    ROLE_OPERATION("OPERATION"), // 관리자
    ROLE_TF("TF"),
    ROLE_GUEST("GUEST"),
    ;
    private final String role;

    Role(String role) {
        this.role = role;
    }

    public String value() {
        return role;
    }
    // 1번
    public static Role getByName(String name) {
        for (Role os : Role.values()) {
            if (os.getRole().equals(name)) {
                return os;
            }
        }
        throw InterviewerInvalidRoleException.EXCEPTION;
    }
}
