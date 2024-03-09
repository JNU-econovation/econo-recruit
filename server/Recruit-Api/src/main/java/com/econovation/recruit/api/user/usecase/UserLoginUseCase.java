package com.econovation.recruit.api.user.usecase;

import com.econovation.recruitcommon.annotation.UseCase;
import com.econovation.recruitcommon.dto.TokenResponse;
import com.econovation.recruitdomain.domains.dto.LoginRequestDto;
import javax.servlet.http.HttpServletResponse;

@UseCase
public interface UserLoginUseCase {
    TokenResponse execute(LoginRequestDto loginRequestDto, HttpServletResponse response);

    TokenResponse refresh(String refreshToken);
}
