package com.econovation.recruit.api.user.service;

import com.econovation.recruit.api.user.usecase.UserLoginUseCase;
import com.econovation.recruit.api.user.usecase.UserRegisterUseCase;
import com.econovation.recruitcommon.dto.TokenResponse;
import com.econovation.recruitcommon.jwt.JwtTokenProvider;
import com.econovation.recruitdomain.domains.dto.LoginRequestDto;
import com.econovation.recruitdomain.domains.dto.SignUpRequestDto;
import com.econovation.recruitdomain.domains.interviewer.domain.Interviewer;
import com.econovation.recruitdomain.domains.interviewer.exception.InterviewerAlreadySubmitException;
import com.econovation.recruitdomain.domains.interviewer.exception.InterviewerNotMatchException;
import com.econovation.recruitdomain.out.InterviewerLoadPort;
import com.econovation.recruitdomain.out.InterviewerRecordPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService implements UserRegisterUseCase, UserLoginUseCase {
    private final InterviewerRecordPort interviewerRecordPort;
    private final InterviewerLoadPort interviewerLoadPort;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public TokenResponse execute(LoginRequestDto loginRequestDto) {
        Interviewer account =
                interviewerLoadPort.loadInterviewerByEmail(loginRequestDto.getEmail());
        checkPassword(loginRequestDto.getPassword(), account.getPassword());
        return jwtTokenProvider.createToken(account.getId(), account.getRole().name());
    }

    private void checkPassword(String password, String encodePassword) {
        boolean isMatch = passwordEncoder.matches(password, encodePassword);
        if (!isMatch) throw InterviewerNotMatchException.EXCEPTION;
    }

    @Override
    @Transactional
    public void signUp(SignUpRequestDto signUpRequestDto) {
        if (interviewerLoadPort.loadOptionalInterviewerByEmail(signUpRequestDto.getEmail()) != null)
            throw InterviewerAlreadySubmitException.EXCEPTION;
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
