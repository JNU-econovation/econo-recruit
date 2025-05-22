package com.econovation.recruitdomain.domains.period.exception;

import com.econovation.recruitcommon.exception.RecruitCodeException;

public class PeriodNotFoundException extends RecruitCodeException {

    public static final PeriodNotFoundException EXCEPTION = new PeriodNotFoundException();

    public PeriodNotFoundException() {
        super(PeriodErrorCode.NOT_FOUND_PERIOD);
    }
}
