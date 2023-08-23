package com.econovation.recruitcommon.exception;

public class OtherServerForbiddenException extends RecruitCodeException {

    public static final RecruitCodeException EXCEPTION = new OtherServerForbiddenException();

    private OtherServerForbiddenException() {
        super(GlobalErrorCode.OTHER_SERVER_FORBIDDEN);
    }
}
