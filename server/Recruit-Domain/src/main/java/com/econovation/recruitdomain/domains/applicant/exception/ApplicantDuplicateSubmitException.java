package com.econovation.recruitdomain.domains.applicant.exception;

import com.econovation.recruitcommon.exception.RecruitCodeException;

public class ApplicantDuplicateSubmitException extends RecruitCodeException {
    public static RecruitCodeException EXCEPTION = new ApplicantDuplicateSubmitException();

    private ApplicantDuplicateSubmitException() {
        super(ApplicantErrorCode.APPLICANT_DUPLICATE_SUBMIT);
    }
}
