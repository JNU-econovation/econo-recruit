package com.econovation.recruitdomain.domains.comment.exception;

import com.econovation.recruitcommon.exception.RecruitCodeException;

public class CommentLikeDuplicatedCreateException extends RecruitCodeException {
    public static final CommentLikeDuplicatedCreateException EXCEPTION =
            new CommentLikeDuplicatedCreateException();

    private CommentLikeDuplicatedCreateException() {
        super(CommentLikeErrorCode.COMMENTLIKE_DUPLICATE_CREATED);
    }
}
