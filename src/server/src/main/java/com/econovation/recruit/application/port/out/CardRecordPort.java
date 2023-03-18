package com.econovation.recruit.application.port.out;

import com.econovation.recruit.domain.card.Card;

public interface CardRecordPort {
    Card save(Card card);

    void delete(Integer cardId);
}
