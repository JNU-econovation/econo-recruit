package com.econovation.recruitdomain.domains.card.exception;

import com.econovation.recruitcommon.exception.RecruitCodeException;

public class CardNotFoundException extends RecruitCodeException {
    public static final CardNotFoundException EXCEPTION = new CardNotFoundException();

    private CardNotFoundException() {
        super(CardErrorCode.CARD_NOT_FOUND);
    }
}
