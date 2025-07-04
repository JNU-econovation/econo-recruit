package com.econovation.recruitdomain.domains.email_verification.adaptor;

import com.econovation.recruitcommon.annotation.Adaptor;
import com.econovation.recruitdomain.domains.email_verification.domain.EmailVerification;
import com.econovation.recruitdomain.domains.email_verification.domain.EmailVerificationRepository;
import com.econovation.recruitdomain.out.EmailVerificationLoadPort;
import com.econovation.recruitdomain.out.EmailVerificationRecordPort;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@Adaptor
@RequiredArgsConstructor
public class EmailVerificationAdaptor
        implements EmailVerificationRecordPort, EmailVerificationLoadPort {
    private final EmailVerificationRepository emailVerificationRepository;

    @Override
    public void save(EmailVerification emailVerification) {
        emailVerificationRepository.save(emailVerification);
    }

    @Override
    public Optional<EmailVerification> loadOptionEmailVerificationByEmail(String email) {
        return emailVerificationRepository.findById(email);
    }

    @Override
    public void delete(String email) {
        emailVerificationRepository.deleteById(email);
    }
}
