package com.econovation.recruitdomain.domains.interviewer.exception;

import com.econovation.recruitcommon.exception.RecruitCodeException;

public class InterviewerNotMatchException extends RecruitCodeException {
    public static final RecruitCodeException EXCEPTION = new InterviewerNotMatchException();

    private InterviewerNotMatchException() {
        super(InterviewerErrorCode.INTERVIEWER_NOT_MATCH);
    }
}
