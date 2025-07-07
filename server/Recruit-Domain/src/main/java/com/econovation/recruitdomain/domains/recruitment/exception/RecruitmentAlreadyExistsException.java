package com.econovation.recruitdomain.domains.recruitment.exception;

import com.econovation.recruitcommon.exception.RecruitCodeException;

public class RecruitmentAlreadyExistsException extends RecruitCodeException {

    public static final RecruitmentAlreadyExistsException EXCEPTION =
            new RecruitmentAlreadyExistsException();

    public RecruitmentAlreadyExistsException() {
        super(RecruitmentErrorCode.RECRUITMENT_ALREADY_EXISTS);
    }
}
