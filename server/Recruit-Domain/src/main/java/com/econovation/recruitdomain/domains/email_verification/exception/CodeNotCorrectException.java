package com.econovation.recruitdomain.domains.email_verification.exception;

import com.econovation.recruitcommon.exception.RecruitCodeException;

public class CodeNotCorrectException extends RecruitCodeException {
    public static final CodeNotCorrectException EXCEPTION = new CodeNotCorrectException();

    private CodeNotCorrectException() {
        super(EmailVerificationErrorCode.CODE_NOT_CORRECT);
    }
}
