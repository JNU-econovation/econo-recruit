package com.econovation.recruitdomain.domains.comment.exception;

import com.econovation.recruitcommon.exception.RecruitCodeException;

public class CommentNotFoundException extends RecruitCodeException {
    public static final CommentNotFoundException EXCEPTION = new CommentNotFoundException();

    private CommentNotFoundException() {
        super(CommentErrorCode.COMMENT_NOT_FOUND);
    }
}
