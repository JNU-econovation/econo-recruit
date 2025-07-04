package com.econovation.recruit.api.user.usecase;

import com.econovation.recruitcommon.annotation.UseCase;
import com.econovation.recruitdomain.domains.dto.VerifyCodeRequestDto;

@UseCase
public interface VerifyCodeUseCase {
    void verifyCode(VerifyCodeRequestDto verifyCodeRequestDto);
}
