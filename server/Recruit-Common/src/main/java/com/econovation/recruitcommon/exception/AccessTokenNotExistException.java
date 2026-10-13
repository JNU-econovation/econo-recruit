package com.econovation.recruitcommon.exception;

public class AccessTokenNotExistException extends RecruitCodeException {
    public static final RecruitCodeException EXCEPTION = new AccessTokenNotExistException();

    private AccessTokenNotExistException() {
        super(GlobalErrorCode.ACCESS_TOKEN_NOT_EXIST);
    }
}
