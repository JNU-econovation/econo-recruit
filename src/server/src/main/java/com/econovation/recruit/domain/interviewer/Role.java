package com.econovation.recruit.domain.interviewer;

public enum Role {
    ROLE_ADMIN("PRESIDENT"),
    ROLE_USER("OPERATION"),
    ROLE_ANONYMOUS("TF");
    String role;

    Role(String role) {
        this.role = role;
    }

    public String value() {
        return role;
    }
}
