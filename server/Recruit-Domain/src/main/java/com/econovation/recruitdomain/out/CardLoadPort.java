package com.econovation.recruitdomain.out;

import com.econovation.recruitdomain.domains.card.domain.Card;
import java.util.List;
import java.util.UUID;

public interface CardLoadPort {

    List<Card> findAll();

    Card findById(Long cardId);


    Card findByApplicantId(UUID applicantId);

    List<Card> findByIdIn(List<Long> cardIds);
}
