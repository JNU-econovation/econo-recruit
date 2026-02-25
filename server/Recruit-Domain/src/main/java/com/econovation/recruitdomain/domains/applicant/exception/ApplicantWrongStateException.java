package com.econovation.recruitdomain.domains.applicant.exception;

import com.econovation.recruitcommon.exception.RecruitCodeException;

public class ApplicantWrongStateException extends RecruitCodeException {

    public static ApplicantWrongStateException wrongStatusException =
            new ApplicantWrongStateException();

    public ApplicantWrongStateException() {
        super(ApplicantErrorCode.INVALID_STATE_TRANSITION);
    }
}
