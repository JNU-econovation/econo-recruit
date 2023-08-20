package com.econovation.recruitdomain.domains.comment.exception;

import com.econovation.recruitcommon.exception.RecruitCodeException;

public class CommentNotHostException extends RecruitCodeException {
    public static final CommentNotHostException EXCEPTION = new CommentNotHostException();

    private CommentNotHostException() {
        super(CommentErrorCode.COMMENT_NOT_HOST);
    }
}
