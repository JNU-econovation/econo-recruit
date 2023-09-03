package com.econovation.recruit.api.user.controller;

import static com.econovation.recruitcommon.consts.RecruitStatic.INTERVIEWER_SUCCESS_SIGNUP_MESSAGE;
import static com.econovation.recruitcommon.consts.RecruitStatic.PASSWORD_SUCCESS_CHANGE_MESSAGE;

import com.econovation.recruit.api.interviewer.docs.InterviewerExceptionDocs;
import com.econovation.recruit.api.user.usecase.UserLoginUseCase;
import com.econovation.recruit.api.user.usecase.UserRegisterUseCase;
import com.econovation.recruitcommon.annotation.ApiErrorExceptionsExample;
import com.econovation.recruitcommon.annotation.DevelopOnlyApi;
import com.econovation.recruitcommon.annotation.PasswordValidate;
import com.econovation.recruitcommon.dto.TokenResponse;
import com.econovation.recruitcommon.jwt.JwtTokenProvider;
import com.econovation.recruitdomain.domains.dto.LoginRequestDto;
import com.econovation.recruitdomain.domains.dto.SignUpRequestDto;
import com.econovation.recruitdomain.domains.interviewer.domain.Role;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Slf4j
@Tag(name = "[0.0]. 유저 관련 API", description = "유저 API")
public class UserController {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRegisterUseCase userRegisterUseCase;
    private final UserLoginUseCase userLoginUseCase;
    private final Long tempId = 0L;

    @DevelopOnlyApi
    @Operation(summary = "임시 토큰을 발급합니다.")
    @GetMapping("/token")
    public ResponseEntity<TokenResponse> issueToken() {
        log.info("tempId: {}", tempId);
        String accessToken = jwtTokenProvider.generateAccessToken(tempId, Role.ROLE_TF.getRole());
        String refreshToken = jwtTokenProvider.generateRefreshToken(tempId);
        log.info("accessToken: {}", accessToken);
        log.info("refreshToken: {}", refreshToken);
        TokenResponse tokenResponse = new TokenResponse(accessToken, refreshToken);
        return new ResponseEntity<>(tokenResponse, HttpStatus.OK);
    }

    @Operation(summary = "로그인합니다.", description = "accessToken, refreshToken을 발급합니다.")
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequestDto loginRequestDto) {
        TokenResponse tokenResponse = userLoginUseCase.execute(loginRequestDto);
        return new ResponseEntity<>(tokenResponse, HttpStatus.OK);
    }

    @Operation(summary = "회원가입합니다.", description = "회원가입합니다.")
    @ApiErrorExceptionsExample(InterviewerExceptionDocs.class)
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody @Valid SignUpRequestDto signUpRequestDto) {
        userRegisterUseCase.signUp(signUpRequestDto);
        return new ResponseEntity<>(INTERVIEWER_SUCCESS_SIGNUP_MESSAGE, HttpStatus.OK);
    }
    @Operation(summary = "토큰 재발행", description = "refreshToken을 이용하여 accessToken을 재발행합니다.")
    @PostMapping("/token/refresh")
    public ResponseEntity<TokenResponse> refresh(@RequestParam String refreshToken) {
        TokenResponse tokenResponse = userLoginUseCase.refresh(refreshToken);
        return new ResponseEntity<>(tokenResponse, HttpStatus.OK);
    }

    @Operation(summary = "비밀번호 수정", description = "비밀번호를 수정합니다.")
    @PostMapping("/password")
    public ResponseEntity<String> changePassword(@RequestParam @Valid @PasswordValidate String password) {
        userRegisterUseCase.changePassword(password);
        return new ResponseEntity<>(PASSWORD_SUCCESS_CHANGE_MESSAGE, HttpStatus.OK);
    }
}
