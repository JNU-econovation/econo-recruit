package com.econovation.recruitdomain.persistence;

import com.econovation.recruitdomain.domains.card.Card;
import com.econovation.recruitdomain.domains.card.CardRepository;
import com.econovation.recruitdomain.domains.card.exception.CardNotFoundException;
import com.econovation.recruitdomain.out.CardLoadPort;
import com.econovation.recruitdomain.out.CardRecordPort;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CardAdapter implements CardLoadPort, CardRecordPort {
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
        return cardRepository
                .findById(cardId)
                .orElseThrow(() -> new IllegalStateException(NO_MATCH_CARD));
    }

    @Override
    public List<Card> findAllByBoardIdIn(List<Integer> boardIds) {
        return cardRepository.findAllByBoardIdIn(boardIds);
    }

    @Override
    public Card findByApplicantId(Integer applicantId) {
        Optional<Card> applicant = cardRepository.findByApplicantId(applicantId);
        if (applicant.isEmpty()) {
            throw CardNotFoundException.EXCEPTION;
        } else{
            return applicant.get();
        }
    }
}
