package com.econovation.recruitdomain.domains.comment.exception;

import com.econovation.recruitcommon.exception.RecruitCodeException;

public class CommentLikeNotFoundException extends RecruitCodeException {
    public static final CommentLikeNotFoundException EXCEPTION = new CommentLikeNotFoundException();

    private CommentLikeNotFoundException() {
        super(CommentLikeErrorCode.COMMENTLIKE_NOT_FOUND);
    }
}
