package com.econovation.recruitdomain.domains.label.exception;

import com.econovation.recruitcommon.exception.RecruitCodeException;

public class LabelDuplicateCreatedException extends RecruitCodeException {
    public static final LabelDuplicateCreatedException EXCEPTION =
            new LabelDuplicateCreatedException();

    private LabelDuplicateCreatedException() {
        super(LabelErrorCode.LABEL_DUPLICATE_SUBMIT);
    }
}
