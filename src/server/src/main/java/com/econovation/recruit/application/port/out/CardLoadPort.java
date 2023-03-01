package com.econovation.recruit.application.port.out;

import com.econovation.recruit.domain.card.Card;

import java.util.List;
import java.util.Map;

public interface CardLoadPort {

    List<Card> findAll();

    Card findById(Integer cardId);
}
