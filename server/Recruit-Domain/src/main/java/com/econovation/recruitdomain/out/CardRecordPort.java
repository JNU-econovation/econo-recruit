package com.econovation.recruitdomain.out;


import com.econovation.recruitdomain.domain.card.Card;

public interface CardRecordPort {
    Card save(Card card);

    void delete(Integer cardId);
}
