package com.econovation.recruitdomain.domains.board.exception;

import com.econovation.recruitcommon.exception.RecruitCodeException;

public class ColumnsNotFoundException extends RecruitCodeException {
    public static final ColumnsNotFoundException EXCEPTION = new ColumnsNotFoundException();

    private ColumnsNotFoundException() {
        super(ColumnsErrorCode.COLUMNS_NOT_FOUND);
    }
}
