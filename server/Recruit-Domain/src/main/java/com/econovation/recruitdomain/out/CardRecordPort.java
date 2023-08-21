package com.econovation.recruitdomain.out;

import com.econovation.recruitdomain.domains.card.domain.Card;

public interface CardRecordPort {
    Card save(Card card);

    void delete(Long cardId);
}
