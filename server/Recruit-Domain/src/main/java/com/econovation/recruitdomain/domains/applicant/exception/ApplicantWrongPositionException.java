package com.econovation.recruitdomain.domains.applicant.exception;

import com.econovation.recruitcommon.exception.RecruitCodeException;

public class ApplicantWrongPositionException extends RecruitCodeException {
    public static RecruitCodeException EXCEPTION = new ApplicantWrongPositionException();

    private ApplicantWrongPositionException() {
        super(ApplicantErrorCode.APPLICANT_WRONG_POSITION);
    }
}
