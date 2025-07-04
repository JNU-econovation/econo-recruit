package com.econovation.recruitdomain.out;

import com.econovation.recruitdomain.domains.email_verification.domain.EmailVerification;

public interface EmailVerificationRecordPort {
    void save(EmailVerification emailVerification);

    void delete(String email);
}
