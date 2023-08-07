package com.econovation.recruitdomain.out;


import com.econovation.recruitdomain.domain.card.Card;
import java.util.List;

public interface CardLoadPort {

    List<Card> findAll();

    Card findById(Integer cardId);
}
