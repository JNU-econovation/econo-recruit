package com.econovation.recruit.api.card.usecase;

import com.econovation.recruitcommon.annotation.UseCase;
import com.econovation.recruitdomain.domains.board.dto.ColumnsResponseDto;
import com.econovation.recruitdomain.domains.card.domain.Card;
import com.econovation.recruitdomain.domains.card.dto.CardResponseDto;
import java.util.List;
import java.util.Map;

@UseCase
public interface CardLoadUseCase {
    List<Card> findAll();

    List<Map<ColumnsResponseDto, CardResponseDto>> getByNavigationId(Integer navigationId);
}
