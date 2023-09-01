package com.econovation.recruit.api.user.service;

import com.econovation.recruit.api.user.usecase.UserLoginUseCase;
import com.econovation.recruit.api.user.usecase.UserRegisterUseCase;
import com.econovation.recruitdomain.domains.dto.LoginRequestDto;
import com.econovation.recruitdomain.domains.dto.SignUpRequestDto;
import com.econovation.recruitdomain.domains.dto.TokenResponse;
import com.econovation.recruitdomain.domains.interviewer.domain.Interviewer;
import com.econovation.recruitdomain.out.InterviewerRecordPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService implements UserRegisterUseCase, UserLoginUseCase {
    private final InterviewerRecordPort interviewerRecordPort;
    private final PasswordEncoder passwordEncoder;

    @Override
    public TokenResponse execute(LoginRequestDto loginRequestDto) {}

    @Override
    @Transactional
    public void signUp(SignUpRequestDto signUpRequestDto) {
        String encededPassword = passwordEncoder.encode(signUpRequestDto.getPassword());
        Interviewer interviewer =
                Interviewer.builder()
                        .year(signUpRequestDto.getYear())
                        .name(signUpRequestDto.getName())
                        .email(signUpRequestDto.getEmail())
                        .password(encededPassword)
                        .build();
        interviewerRecordPort.save(interviewer);
    }
}
