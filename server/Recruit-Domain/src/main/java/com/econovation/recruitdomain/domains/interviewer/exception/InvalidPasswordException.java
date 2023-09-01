package com.econovation.recruitdomain.domains.interviewer.exception;

import com.econovation.recruitcommon.exception.RecruitCodeException;

public class InvalidPasswordException extends RecruitCodeException {
    public static final InvalidPasswordException EXCEPTION = new InvalidPasswordException();

    private InvalidPasswordException() {
        super(InterviewerErrorCode.INVALID_PASSWORD);
    }
}
