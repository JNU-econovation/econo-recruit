package com.econovation.recruit.utils.sort.strategy.applicant;

import com.econovation.recruit.utils.sort.strategy.SortStrategy;
import com.econovation.recruitdomain.domains.applicant.domain.MongoAnswer;

public class NameAscendingSortingStrategy implements SortStrategy<MongoAnswer> {
    @Override
    public int compare(MongoAnswer obj1, MongoAnswer obj2) {
        return obj1.getQna().get("name").toString().compareTo(obj2.getQna().get("name").toString());
    }
}
