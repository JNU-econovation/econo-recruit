package com.econovation.recruit.adapter.out.persistence;

import com.econovation.recruit.application.port.out.CardLoadPort;
import com.econovation.recruit.application.port.out.CardRecordPort;
import com.econovation.recruit.domain.card.Card;
import com.econovation.recruit.domain.card.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CardPersistenceAdapter implements CardLoadPort, CardRecordPort {
    private static final String NO_MATCH_CARD = "일치하는 Card 가 존재하지 않습니다";
    private final CardRepository cardRepository;
    @Override
    public Card save(Card card) {
        return cardRepository.save(card);
    }

    @Override
    public void delete(Integer cardId) {
        cardRepository.deleteById(cardId);

    }

    @Override
    public List<Card> findAll() {
        List<Card> all = cardRepository.findAll();
        return all;
    }

    @Override
    public Card findById(Integer cardId) {
        return cardRepository.findById(cardId).orElseThrow(() -> new IllegalStateException(NO_MATCH_CARD));
    }
}
