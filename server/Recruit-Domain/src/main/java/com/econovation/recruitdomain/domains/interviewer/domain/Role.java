package com.econovation.recruitdomain.domains.interviewer.domain;

import lombok.Getter;

@Getter
public enum Role {
    ROLE_PRESIDENT("PRESIDENT"),
    ROLE_OPERATION("OPERATION"),
    ROLE_TF("TF");
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
        return null;
    }
}
