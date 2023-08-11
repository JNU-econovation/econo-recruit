package com.econovation.recruit.application.port.in;

import com.econovation.recruitdomain.domains.card.Card;
import com.econovation.recruitdomain.domains.dto.CreateWorkCardDto;
import java.util.List;

public interface CardRegisterUseCase {
    List<Card> findAll();

    void deleteById(Integer cardId);

    void saveWorkCard(CreateWorkCardDto createWorkCardDto);
}
