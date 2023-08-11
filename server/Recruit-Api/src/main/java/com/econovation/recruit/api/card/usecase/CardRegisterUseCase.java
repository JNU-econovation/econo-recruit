package com.econovation.recruit.api.card.usecase;

import com.econovation.recruitcommon.annotation.UseCase;
import com.econovation.recruitdomain.domains.card.Card;
import com.econovation.recruitdomain.domains.dto.CreateWorkCardDto;
import java.util.List;
import java.util.UUID;

@UseCase
public interface CardRegisterUseCase {

    void deleteById(Integer cardId);

    void saveWorkCard(CreateWorkCardDto createWorkCardDto);
    void saveApplicantCard(UUID applicantId);
}
