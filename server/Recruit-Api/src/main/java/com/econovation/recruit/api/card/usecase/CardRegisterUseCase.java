package com.econovation.recruit.api.card.usecase;

import com.econovation.recruitcommon.annotation.UseCase;
import com.econovation.recruitdomain.domains.dto.CreateWorkCardDto;
import com.econovation.recruitdomain.domains.dto.UpdateWorkCardDto;

@UseCase
public interface CardRegisterUseCase {
    void deleteById(Long cardId);
    void saveWorkCard(CreateWorkCardDto createWorkCardDto);
    void update(Long cardId, UpdateWorkCardDto updateWorkCardDto);
}
