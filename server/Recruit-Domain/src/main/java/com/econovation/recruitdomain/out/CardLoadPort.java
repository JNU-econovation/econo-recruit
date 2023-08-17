package com.econovation.recruitdomain.out;

import com.econovation.recruitdomain.domains.card.Card;
import java.util.List;

public interface CardLoadPort {

    List<Card> findAll();

    Card findById(Integer cardId);

    List<Card> findAllByBoardIdIn(List<Integer> collect);
}
