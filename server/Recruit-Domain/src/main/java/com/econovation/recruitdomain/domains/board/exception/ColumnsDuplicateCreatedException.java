package com.econovation.recruitdomain.domains.board.exception;

import com.econovation.recruitcommon.exception.RecruitCodeException;

public class ColumnsDuplicateCreatedException extends RecruitCodeException {
    public static final ColumnsDuplicateCreatedException EXCEPTION =
            new ColumnsDuplicateCreatedException();

    private ColumnsDuplicateCreatedException() {
        super(ColumnsErrorCode.COLUMNS_DUPLICATE_CREATED);
    }
}
