package com.econovation.recruitcommon.exception;

public class InvalidPasswordException extends RecruitCodeException {
    public static final RecruitCodeException EXCEPTION = new InvalidPasswordException();

    private InvalidPasswordException() {
        super(GlobalErrorCode.INVALID_PASSWORD);
    }
}
