package com.econovation.recruitdomain.domains.interviewer.exception;

import com.econovation.recruitcommon.exception.RecruitCodeException;

public class InterviewerDuplicateCreateException extends RecruitCodeException {
    public static final InterviewerDuplicateCreateException EXCEPTION =
            new InterviewerDuplicateCreateException();

    private InterviewerDuplicateCreateException() {
        super(InterviewerErrorCode.INTERVIEWER_DUPLICATE_CREATED);
    }
}
