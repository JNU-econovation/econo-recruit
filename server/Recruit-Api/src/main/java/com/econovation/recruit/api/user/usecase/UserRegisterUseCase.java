package com.econovation.recruit.api.user.usecase;

import com.econovation.recruitcommon.annotation.UseCase;
import com.econovation.recruitdomain.domains.dto.SignUpRequestDto;

@UseCase
public interface UserRegisterUseCase {

    void signUp(SignUpRequestDto signUpRequestDto);

    void changePassword(String password);
}
