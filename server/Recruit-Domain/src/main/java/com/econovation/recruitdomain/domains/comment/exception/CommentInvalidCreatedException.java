package com.econovation.recruitdomain.domains.comment.exception;

import com.econovation.recruitcommon.exception.RecruitCodeException;

public class CommentInvalidCreatedException extends RecruitCodeException {
    public static final CommentInvalidCreatedException EXCEPTION =
            new CommentInvalidCreatedException();

    private CommentInvalidCreatedException() {
        super(CommentErrorCode.COMMENT_INVALID_CREATED);
    }
}
