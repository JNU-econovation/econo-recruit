package com.econovation.recruitdomain.domains.board.exception;

import com.econovation.recruitcommon.exception.RecruitCodeException;
import com.econovation.recruitdomain.domains.card.exception.CardErrorCode;

public class CardDuplicateCreatedException extends RecruitCodeException {
    public static final CardDuplicateCreatedException EXCEPTION =
            new CardDuplicateCreatedException();

    private CardDuplicateCreatedException() {
        super(CardErrorCode.CARD_DUPLICATE_CREATED);
    }
}
