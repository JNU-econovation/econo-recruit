package com.econovation.recruitdomain.domains.applicant.exception;

import com.econovation.recruitcommon.exception.RecruitCodeException;

public class ApplicantNotFoundException extends RecruitCodeException {
    public static RecruitCodeException EXCEPTION = new ApplicantNotFoundException();

    private ApplicantNotFoundException() {
        super(ApplicantErrorCode.APPLICANT_NOT_FOUND);
    }
}
