package com.econovation.recruit.adapter.out.persistence;

import com.econovation.recruit.application.port.out.CardLoadPort;
import com.econovation.recruit.application.port.out.CardRecordPort;
import com.econovation.recruit.domain.card.Card;
import com.econovation.recruit.domain.card.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CardPersistenceAdapter implements CardLoadPort, CardRecordPort {
    private final CardRepository cardRepository;
    @Override
    public Card save(Card card) {
        return cardRepository.save(card);
    }
}
