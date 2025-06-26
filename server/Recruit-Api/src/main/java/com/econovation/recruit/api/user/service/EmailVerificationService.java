package com.econovation.recruit.api.user.service;

import com.econovation.recruit.api.user.usecase.SendEmailUseCase;
import com.econovation.recruitdomain.domains.dto.SendEmailRequestDto;
import com.econovation.recruitdomain.domains.email_verification.domain.EmailVerification;
import com.econovation.recruitdomain.out.EmailVerificationLoadPort;
import com.econovation.recruitdomain.out.EmailVerificationRecordPort;
import com.econovation.recruitinfrastructure.apache.EmailVerificationSender;
import java.security.SecureRandom;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailVerificationService implements SendEmailUseCase {
    private final EmailVerificationRecordPort emailVerificationRecordPort;
    private final EmailVerificationLoadPort emailVerificationLoadPort;
    private final EmailVerificationSender emailVerificationSender;

    private static final String VERIFIED_PREFIX = ":verified";
    private static final long EMAIL_VERIFICATION_CODE_EXPIRE = Duration.ofMinutes(5).getSeconds();

    @Override
    public void sendEmail(SendEmailRequestDto sendEmailRequestDto) {
        String email = sendEmailRequestDto.getEmail();

        if (emailVerificationLoadPort
                .loadOptionEmailVerificationByEmail(email + VERIFIED_PREFIX)
                .isPresent()) {
            emailVerificationRecordPort.delete(email);
        }

        String code = createCode();
        EmailVerification emailVerification =
                EmailVerification.builder()
                        .email(email)
                        .code(code)
                        .expiration(EMAIL_VERIFICATION_CODE_EXPIRE)
                        .build();
        emailVerificationRecordPort.save(emailVerification);
        emailVerificationSender.sendVerificationCode(sendEmailRequestDto.getEmail(), code);
    }

    private String createCode() {
        SecureRandom secureRandom = new SecureRandom();
        int code = 100000 + secureRandom.nextInt(900000);
        return String.valueOf(code);
    }
}
