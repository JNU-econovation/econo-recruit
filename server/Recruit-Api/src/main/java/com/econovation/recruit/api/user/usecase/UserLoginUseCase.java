package com.econovation.recruit.api.user.usecase;

import com.econovation.recruitcommon.annotation.UseCase;
import com.econovation.recruitcommon.dto.TokenResponse;
import com.econovation.recruitdomain.domains.dto.LoginRequestDto;

@UseCase
public interface UserLoginUseCase {
    TokenResponse execute(LoginRequestDto loginRequestDto);
}
