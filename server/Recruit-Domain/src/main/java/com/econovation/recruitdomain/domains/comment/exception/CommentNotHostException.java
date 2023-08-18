package com.econovation.recruitdomain.domains.comment.exception;

import com.econovation.recruitcommon.exception.RecruitCodeException;
import com.econovation.recruitdomain.domains.card.exception.CardErrorCode;

public class CommentNotHostException extends RecruitCodeException {
    public static final CommentNotHostException EXCEPTION = new CommentNotHostException();

    private CommentNotHostException() {
        super(CommentErrorCode.COMMENT_NOT_HOST);
    }
}
