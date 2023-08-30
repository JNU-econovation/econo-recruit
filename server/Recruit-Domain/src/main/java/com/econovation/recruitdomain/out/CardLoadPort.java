package com.econovation.recruitdomain.out;

import com.econovation.recruitdomain.domains.card.domain.Card;
import java.util.List;

public interface CardLoadPort {

    List<Card> findAll();

    Card findById(Long cardId);

    Card findByApplicantId(String applicantId);

    List<Card> findByIdIn(List<Long> cardIds);
}
