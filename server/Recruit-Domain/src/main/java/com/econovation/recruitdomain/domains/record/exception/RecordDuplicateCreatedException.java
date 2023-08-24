package com.econovation.recruitdomain.domains.record.exception;

import com.econovation.recruitcommon.exception.RecruitCodeException;

public class RecordDuplicateCreatedException extends RecruitCodeException {
    public static final RecordDuplicateCreatedException EXCEPTION =
            new RecordDuplicateCreatedException();

    private RecordDuplicateCreatedException() {
        super(RecordErrorCode.RECORD_DUPLICATE_SUBMIT);
    }
}
