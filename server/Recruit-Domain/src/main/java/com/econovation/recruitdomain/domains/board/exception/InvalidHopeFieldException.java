package com.econovation.recruitdomain.domains.board.exception;

import com.econovation.recruitcommon.exception.RecruitCodeException;

public class InvalidHopeFieldException extends RecruitCodeException {
    public static final InvalidHopeFieldException EXCEPTION = new InvalidHopeFieldException();

    private InvalidHopeFieldException() {
        super(BoardErrorCode.INVALID_HOPE_FIELD);
    }
}
