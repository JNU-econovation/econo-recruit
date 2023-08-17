package com.econovation.recruitdomain.domains.label.exception;

import com.econovation.recruitcommon.exception.RecruitCodeException;

public class LabelNotFoundException extends RecruitCodeException {
    public static final LabelNotFoundException EXCEPTION = new LabelNotFoundException();

    private LabelNotFoundException() {
        super(LabelErrorCode.LABEL_DUPLICATE_SUBMIT);
    }
}
