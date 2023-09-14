package com.econovation.recruitdomain.domains.applicant.exception;

import com.econovation.recruitcommon.exception.RecruitCodeException;

public class ApplicantOutOfDateException extends RecruitCodeException {
    public static RecruitCodeException EXCEPTION = new ApplicantOutOfDateException();

    private ApplicantOutOfDateException() {
    super(AnswerErrorCode.APPLICANT_OUT_OF_DATE);
    }
}
