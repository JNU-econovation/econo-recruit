package com.econovation.recruitdomain.domains.interviewer.exception;

import com.econovation.recruitcommon.exception.RecruitCodeException;

public class InvalidAccessTokenException extends RecruitCodeException {
    public static final InvalidAccessTokenException EXCEPTION = new InvalidAccessTokenException();
    private InvalidAccessTokenException() {
        super(InterviewerErrorCode.INVALID_ACCESS_TOKEN);
    }
}
