package com.econovation.recruit.api.user.service;

import com.econovation.recruit.api.user.usecase.SendEmailUseCase;
import com.econovation.recruit.api.user.usecase.VerifyCodeUseCase;
import com.econovation.recruitdomain.domains.dto.SendEmailRequestDto;
import com.econovation.recruitdomain.domains.dto.VerifyCodeRequestDto;
import com.econovation.recruitdomain.domains.email_verification.domain.EmailVerification;
import com.econovation.recruitdomain.domains.email_verification.exception.CodeNotCorrectException;
import com.econovation.recruitdomain.domains.email_verification.exception.CodeNotFoundException;
import com.econovation.recruitdomain.out.EmailVerificationLoadPort;
import com.econovation.recruitdomain.out.EmailVerificationRecordPort;
import com.econovation.recruitinfrastructure.apache.EmailVerificationSender;
import java.security.SecureRandom;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailVerificationService implements SendEmailUseCase, VerifyCodeUseCase {
    private final EmailVerificationRecordPort emailVerificationRecordPort;
    private final EmailVerificationLoadPort emailVerificationLoadPort;
    private final EmailVerificationSender emailVerificationSender;

    private static final String VERIFIED_PREFIX = ":verified";
    private static final long EMAIL_VERIFICATION_CODE_EXPIRE = Duration.ofMinutes(5).getSeconds();
    private static final long EMAIL_VERIFIED_EXPIRE = Duration.ofHours(1).getSeconds();
    private static final String TRUE = Boolean.TRUE.toString();

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

    @Override
    public void verifyCode(VerifyCodeRequestDto verifyCodeRequestDto) {
        String email = verifyCodeRequestDto.getEmail();
        EmailVerification emailVerification = emailVerificationLoadPort.loadOptionEmailVerificationByEmail(email).orElseThrow(() -> CodeNotFoundException.EXCEPTION);
        if (!emailVerification.getCode().equals(verifyCodeRequestDto.getCode())) {
            throw CodeNotCorrectException.EXCEPTION;
        }
        EmailVerification verified =
                EmailVerification.builder()
                        .email(email + VERIFIED_PREFIX)
                        .code(TRUE)
                        .expiration(EMAIL_VERIFIED_EXPIRE)
                        .build();
        emailVerificationRecordPort.save(verified);
        emailVerificationRecordPort.delete(email);
    }

    private String createCode() {
        SecureRandom secureRandom = new SecureRandom();
        int code = 100000 + secureRandom.nextInt(900000);
        return String.valueOf(code);
    }
}
