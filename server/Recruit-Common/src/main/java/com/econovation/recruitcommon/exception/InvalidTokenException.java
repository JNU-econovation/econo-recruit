package com.econovation.recruitcommon.exception;

public class InvalidTokenException extends RecruitCodeException {
    public static final RecruitCodeException EXCEPTION = new InvalidTokenException();

    private InvalidTokenException() {
        super(GlobalErrorCode.INVALID_TOKEN);
    }
}
