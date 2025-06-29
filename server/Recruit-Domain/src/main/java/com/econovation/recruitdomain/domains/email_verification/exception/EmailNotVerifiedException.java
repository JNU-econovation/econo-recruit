package com.econovation.recruitdomain.domains.email_verification.exception;

import com.econovation.recruitcommon.exception.RecruitCodeException;

public class EmailNotVerifiedException extends RecruitCodeException {
    public static final EmailNotVerifiedException EXCEPTION = new EmailNotVerifiedException();

    private EmailNotVerifiedException() {
        super(EmailVerificationErrorCode.EMAIL_NOT_VERIFIED);
    }
}
