package com.econovation.recruitdomain.domains.email_verification.exception;

import com.econovation.recruitcommon.exception.RecruitCodeException;

public class CodeNotFoundException extends RecruitCodeException {
    public static final CodeNotFoundException EXCEPTION = new CodeNotFoundException();
    private CodeNotFoundException() {
        super(EmailVerificationErrorCode.CODE_NOT_FOUND);
    }
}
