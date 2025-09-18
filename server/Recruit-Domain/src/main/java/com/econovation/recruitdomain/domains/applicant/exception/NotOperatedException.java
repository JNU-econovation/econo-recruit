package com.econovation.recruitdomain.domains.applicant.exception;

import com.econovation.recruitcommon.exception.RecruitCodeException;

public class NotOperatedException extends RecruitCodeException {

    public static NotOperatedException EXCEPTION = new NotOperatedException();

    public NotOperatedException(){
        super(ApplicantErrorCode.NOT_OPERATED);
    }

}
