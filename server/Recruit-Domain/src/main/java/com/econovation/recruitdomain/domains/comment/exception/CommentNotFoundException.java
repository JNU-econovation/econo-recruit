package com.econovation.recruitdomain.domains.comment.exception;

import com.econovation.recruitcommon.exception.RecruitCodeException;
import com.econovation.recruitdomain.domains.card.exception.CardErrorCode;

public class CommentNotFoundException extends RecruitCodeException {
    public static final CommentNotFoundException EXCEPTION = new CommentNotFoundException();

    private CommentNotFoundException() {
        super(CardErrorCode.CARD_NOT_FOUND);
    }
}
