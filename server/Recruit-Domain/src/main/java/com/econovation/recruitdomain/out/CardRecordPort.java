package com.econovation.recruitdomain.out;

import com.econovation.recruitdomain.domains.card.domain.Card;
import java.util.List;

public interface CardRecordPort {
    Card save(Card card);

    void delete(Long cardId);

    void deleteAllByApplicantIds(List<String> applicantIds);
}
