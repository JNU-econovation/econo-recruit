package com.econovation.recruitdomain.out;

import com.econovation.recruitdomain.domains.card.Card;
import java.util.List;

public interface CardLoadPort {

    List<Card> findAll();

    Card findById(Long cardId);

    List<Card> findAllByBoardIdIn(List<Integer> collect);

    Card findByApplicantId(Integer applicantId);
}
