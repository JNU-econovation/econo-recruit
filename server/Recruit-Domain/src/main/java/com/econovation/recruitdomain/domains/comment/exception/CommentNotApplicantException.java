package com.econovation.recruitdomain.domains.comment.exception;

import com.econovation.recruitcommon.exception.RecruitCodeException;

public class CommentNotApplicantException extends RecruitCodeException {
    public static final CommentNotApplicantException EXCEPTION = new CommentNotApplicantException();

    private CommentNotApplicantException() {
        super(CommentErrorCode.COMMENT_NOT_APPLICANT);
    }
}
