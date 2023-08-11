package com.econovation.recruitdomain.domains.board.domain;

public enum CardType {
    WORK_CARD("work_card"),
    APPLICANT("applicant"),
    ;

    private final String type;

    CardType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
