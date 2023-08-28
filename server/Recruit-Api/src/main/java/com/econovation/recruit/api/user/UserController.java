package com.econovation.recruit.api.user;

import com.econovation.recruitcommon.annotation.DevelopOnlyApi;
import com.econovation.recruitcommon.jwt.JwtTokenProvider;
import com.econovation.recruitdomain.domains.dto.TokenResponse;
import com.econovation.recruitdomain.domains.interviewer.domain.Role;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Slf4j
@Tag(name = "[0.0]. 유저 관련 임시 API", description = "유저 관련 API")
public class UserController {
    private final JwtTokenProvider jwtTokenProvider;
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
}
