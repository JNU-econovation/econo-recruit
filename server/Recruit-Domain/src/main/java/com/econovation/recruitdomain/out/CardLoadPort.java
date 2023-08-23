package com.econovation.recruitdomain.out;

import com.econovation.recruitdomain.domains.card.domain.Card;
import java.util.List;

public interface CardLoadPort {

    List<Card> findAll();

    Card findById(Long cardId);

    List<Card> findAllByBoardIdIn(List<Integer> collect);

    Card findByApplicantId(UUID applicantId);
}
