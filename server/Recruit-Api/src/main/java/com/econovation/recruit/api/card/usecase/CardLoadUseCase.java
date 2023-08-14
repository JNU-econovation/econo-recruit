package com.econovation.recruit.api.card.usecase;

import com.econovation.recruitcommon.annotation.UseCase;
import com.econovation.recruitdomain.domains.card.Card;
import java.util.List;

@UseCase
public interface CardLoadUseCase {
    List<Card> findAll();
}
