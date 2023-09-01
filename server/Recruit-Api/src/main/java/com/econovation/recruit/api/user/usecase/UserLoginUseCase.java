package com.econovation.recruit.api.user.usecase;

import com.econovation.recruitcommon.annotation.UseCase;
import com.econovation.recruitdomain.domains.dto.LoginRequestDto;
import com.econovation.recruitdomain.domains.dto.TokenResponse;

@UseCase
public interface UserLoginUseCase {
    TokenResponse execute(LoginRequestDto loginRequestDto);
}
