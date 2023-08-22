package com.econovation.recruitdomain.domains.interviewer.exception;

import com.econovation.recruitcommon.exception.RecruitCodeException;

public class InterviewerInvalidRoleException extends RecruitCodeException {
    public static final InterviewerInvalidRoleException EXCEPTION =
            new InterviewerInvalidRoleException();

    private InterviewerInvalidRoleException() {
        super(InterviewerErrorCode.INTERVIEWER_INVALID_ROLE);
    }
}
