package com.econovation.recruit.api.user.usecase;

import com.econovation.recruitcommon.annotation.UseCase;
import com.econovation.recruitdomain.domains.dto.SendEmailRequestDto;

@UseCase
public interface SendEmailUseCase {

    void sendEmailForPassword(SendEmailRequestDto sendEmailRequestDto);

    void sendEmailForSignup(SendEmailRequestDto sendEmailRequestDto);
}
