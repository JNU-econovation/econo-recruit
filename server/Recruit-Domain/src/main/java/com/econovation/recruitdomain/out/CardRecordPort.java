package com.econovation.recruitdomain.out;

import com.econovation.recruitdomain.domains.card.Card;

public interface CardRecordPort {
    Card save(Card card);

    void delete(Long cardId);
}
