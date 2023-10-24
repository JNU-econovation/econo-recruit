package com.econovation.recruitdomain.domains.record.exception;

import com.econovation.recruitcommon.exception.RecruitCodeException;

public class RecordNotFoundException extends RecruitCodeException {
    public static final RecordNotFoundException EXCEPTION = new RecordNotFoundException();

    private RecordNotFoundException() {
        super(RecordErrorCode.RECORD_NOT_FOUND);
    }
}
