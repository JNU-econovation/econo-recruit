package com.econovation.recruit.utils.sort.strategy;

import com.econovation.recruitdomain.domains.applicant.domain.MongoAnswer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("answerNameSort")
public class AnswerNameAscendingSortingStrategy implements SortStrategy<MongoAnswer> {
    @Override
    public int compare(MongoAnswer obj1, MongoAnswer obj2) {
        return obj1.getQna().get("name").toString().compareTo(obj2.getQna().get("name").toString());
    }
}
