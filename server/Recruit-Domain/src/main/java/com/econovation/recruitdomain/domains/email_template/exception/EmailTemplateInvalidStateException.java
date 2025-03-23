package com.econovation.recruitdomain.domains.email_template.exception;

import com.econovation.recruitcommon.exception.RecruitCodeException;

public class EmailTemplateInvalidStateException extends RecruitCodeException {

    public static final EmailTemplateInvalidStateException EXCEPTION =
            new EmailTemplateInvalidStateException();
    private EmailTemplateInvalidStateException() {
        super(EmailTemplateErrorCode.EMAIL_TEMPLATE_INVALID_STATE);
    }

}
