package com.econovation.recruitdomain.domains.interviewer.exception;

import com.econovation.recruitcommon.exception.RecruitCodeException;

public class InterviewerNotOneTypeException extends RecruitCodeException {
    public static final InterviewerNotOneTypeException EXCEPTION =
            new InterviewerNotOneTypeException();

    private InterviewerNotOneTypeException() {
        super(InterviewerErrorCode.INTERVIEWER_NOT_ONE_TYPE);
    }
}
