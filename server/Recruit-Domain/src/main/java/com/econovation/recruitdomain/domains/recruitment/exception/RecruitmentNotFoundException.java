package com.econovation.recruitdomain.domains.recruitment.exception;

import com.econovation.recruitcommon.exception.RecruitCodeException;

public class RecruitmentNotFoundException extends RecruitCodeException {

    public static final RecruitmentNotFoundException EXCEPTION = new RecruitmentNotFoundException();

    public RecruitmentNotFoundException() {
        super(RecruitmentErrorCode.RECRUITMENT_NOT_FOUND);
    }
}
