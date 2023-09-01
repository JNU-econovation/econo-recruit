package com.econovation.recruitdomain.domains.interviewer.exception;

import com.econovation.recruitcommon.exception.RecruitCodeException;

public class InterviewerAlreadySubmitException extends RecruitCodeException {
    public static final InterviewerAlreadySubmitException EXCEPTION = new InterviewerAlreadySubmitException();

    private InterviewerAlreadySubmitException() {
        super(InterviewerErrorCode.INTERVIEWER_ALREADY_SUBMIT);
    }
}
