package com.econovation.recruitdomain.domains.recruitment.exception;

import com.econovation.recruitcommon.exception.RecruitCodeException;

public class RecruitmentInValidDateException extends RecruitCodeException {

    public static final RecruitmentInValidDateException EXCEPTION_1 = new RecruitmentInValidDateException(RecruitmentErrorCode.RECRUITMENT_INVALID_DATE_1);
    public static final RecruitmentInValidDateException EXCEPTION_2 = new RecruitmentInValidDateException(RecruitmentErrorCode.RECRUITMENT_INVALID_DATE_2);

    public RecruitmentInValidDateException(RecruitmentErrorCode errorCode){
        super(errorCode);
    }

}
