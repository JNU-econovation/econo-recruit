package com.econovation.recruitdomain.domains.applicant.exception;

import com.econovation.recruitcommon.exception.RecruitCodeException;

public class ApplicantProhibitDeleteException extends RecruitCodeException {
    public static RecruitCodeException EXCEPTION = new ApplicantProhibitDeleteException();

    private ApplicantProhibitDeleteException() {
        super(ApplicantErrorCode.APPLICANT_PROHIBIT_DELETE);
    }
}
