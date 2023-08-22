package com.econovation.recruitcommon.exception;

public class OtherServerExpiredTokenException extends RecruitCodeException {

    public static final RecruitCodeException EXCEPTION = new OtherServerExpiredTokenException();

    private OtherServerExpiredTokenException() {
        super(GlobalErrorCode.OTHER_SERVER_EXPIRED_TOKEN);
    }
}
