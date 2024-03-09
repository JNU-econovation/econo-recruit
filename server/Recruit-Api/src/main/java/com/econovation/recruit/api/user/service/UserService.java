package com.econovation.recruit.api.user.service;

import com.econovation.recruit.api.config.security.SecurityUtils;
import com.econovation.recruit.api.user.usecase.UserLoginUseCase;
import com.econovation.recruit.api.user.usecase.UserRegisterUseCase;
import com.econovation.recruitcommon.consts.RecruitStatic;
import com.econovation.recruitcommon.dto.TokenResponse;
import com.econovation.recruitcommon.jwt.JwtTokenProvider;
import com.econovation.recruitdomain.domains.dto.LoginRequestDto;
import com.econovation.recruitdomain.domains.dto.SignUpRequestDto;
import com.econovation.recruitdomain.domains.interviewer.domain.Interviewer;
import com.econovation.recruitdomain.domains.interviewer.domain.Role;
import com.econovation.recruitdomain.domains.interviewer.exception.InterviewerAlreadySubmitException;
import com.econovation.recruitdomain.domains.interviewer.exception.InterviewerNotMatchException;
import com.econovation.recruitdomain.out.InterviewerLoadPort;
import com.econovation.recruitdomain.out.InterviewerRecordPort;
import javax.servlet.http.HttpServletResponse;
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
    public TokenResponse execute(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        Interviewer account =
                interviewerLoadPort.loadInterviewerByEmail(loginRequestDto.getEmail());
        if (checkPassword(loginRequestDto.getPassword(), account.getPassword())) {
            TokenResponse tokenResponse =
                    jwtTokenProvider.createToken(account.getId(), account.getRole().name());
            response.addHeader(
                    RecruitStatic.SET_COOKIE,
                    com.econovation.recruit.utils.SecurityUtils.setCookie(
                                    "refreshToken", tokenResponse.getRefreshToken())
                            .toString());
            response.addHeader(
                    RecruitStatic.SET_COOKIE,
                    com.econovation.recruit.utils.SecurityUtils.setCookie(
                                    "accessToken", tokenResponse.getAccessToken())
                            .toString());
            return tokenResponse;
        } else {
            response.addHeader(
                    RecruitStatic.SET_COOKIE,
                    com.econovation.recruit.utils.SecurityUtils.logoutCookie("refreshToken", null)
                            .toString());
            response.addHeader(
                    RecruitStatic.SET_COOKIE,
                    com.econovation.recruit.utils.SecurityUtils.logoutCookie("accessToken", null)
                            .toString());
            throw InterviewerNotMatchException.EXCEPTION;
        }
    }

    @Override
    public TokenResponse refresh(String refreshToken) {
        Long idpId = jwtTokenProvider.parseRefreshToken(refreshToken);
        Interviewer account = interviewerLoadPort.loadInterviewById(idpId);
        return jwtTokenProvider.createToken(account.getId(), account.getRole().name());
    }

    private boolean checkPassword(String password, String encodePassword) {
        return passwordEncoder.matches(password, encodePassword);
    }

    @Override
    @Transactional
    public void signUp(SignUpRequestDto signUpRequestDto) {
        if (interviewerLoadPort
                .loadOptionalInterviewerByEmail(signUpRequestDto.getEmail())
                .isPresent()) throw InterviewerAlreadySubmitException.EXCEPTION;
        String encededPassword = passwordEncoder.encode(signUpRequestDto.getPassword());
        Interviewer interviewer =
                Interviewer.builder()
                        .year(signUpRequestDto.getYear())
                        .name(signUpRequestDto.getName())
                        .email(signUpRequestDto.getEmail())
                        .password(encededPassword)
                        .role(Role.ROLE_GUEST)
                        .build();
        interviewerRecordPort.save(interviewer);
    }

    @Override
    @Transactional
    public void changePassword(String password) {
        Long userId = SecurityUtils.getCurrentUserId();
        String encededPassword = passwordEncoder.encode(password);
        interviewerLoadPort.loadInterviewById(userId).changePassword(encededPassword);
    }
}
