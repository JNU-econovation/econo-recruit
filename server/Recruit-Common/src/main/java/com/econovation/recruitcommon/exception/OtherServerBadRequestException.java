package com.econovation.recruitcommon.exception;

public class OtherServerBadRequestException extends RecruitCodeException {

    public static final RecruitCodeException EXCEPTION = new OtherServerBadRequestException();

    private OtherServerBadRequestException() {
        super(GlobalErrorCode.OTHER_SERVER_BAD_REQUEST);
    }
}
