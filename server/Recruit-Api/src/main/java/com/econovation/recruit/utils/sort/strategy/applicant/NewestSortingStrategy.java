package com.econovation.recruit.utils.sort.strategy.applicant;

import com.econovation.recruit.utils.sort.strategy.SortStrategy;
import com.econovation.recruitdomain.domains.applicant.domain.MongoAnswer;

public class NewestSortingStrategy implements SortStrategy<MongoAnswer> {
    @Override
    public int compare(MongoAnswer obj1, MongoAnswer obj2) {
        //         createdAt 값을 가져와 비교하여 정렬 순서를 결정합니다.
        return obj1.getCreatedDate().compareTo(obj2.getCreatedDate());
    }
}
