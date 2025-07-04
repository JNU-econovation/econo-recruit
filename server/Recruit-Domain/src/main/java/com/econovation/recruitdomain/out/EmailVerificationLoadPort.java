package com.econovation.recruitdomain.out;

import com.econovation.recruitdomain.domains.email_verification.domain.EmailVerification;
import java.util.Optional;

public interface EmailVerificationLoadPort {
    Optional<EmailVerification> loadOptionEmailVerificationByEmail(String email);
}
