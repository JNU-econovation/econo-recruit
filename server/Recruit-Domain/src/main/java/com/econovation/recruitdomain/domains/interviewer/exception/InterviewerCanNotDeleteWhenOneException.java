package com.econovation.recruitdomain.domains.interviewer.exception;

import com.econovation.recruitcommon.exception.RecruitCodeException;

public class InterviewerCanNotDeleteWhenOneException extends RecruitCodeException {
    public static final InterviewerCanNotDeleteWhenOneException EXCEPTION =
            new InterviewerCanNotDeleteWhenOneException();

    private InterviewerCanNotDeleteWhenOneException() {
        super(InterviewerErrorCode.INTERVIEWER_CAN_NOT_DELETE_WHEN_ONE);
    }
}
