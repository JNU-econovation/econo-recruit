package com.econovation.recruitdomain.domains.comment.exception;

import com.econovation.recruitcommon.exception.RecruitCodeException;

public class CommentDisclosureNotFoundException extends RecruitCodeException {
    public static final CommentDisclosureNotFoundException EXCEPTION = new CommentDisclosureNotFoundException();

    private CommentDisclosureNotFoundException() {
        super(CommentDisclosureErrorCode.COMMENT_DISCLOSURE_NOT_FOUND);
    }
}
