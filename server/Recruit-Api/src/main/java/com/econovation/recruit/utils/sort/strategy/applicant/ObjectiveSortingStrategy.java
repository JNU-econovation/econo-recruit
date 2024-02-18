package com.econovation.recruit.utils.sort.strategy.applicant;

import com.econovation.recruit.utils.sort.strategy.SortStrategy;
import com.econovation.recruitdomain.domains.applicant.domain.MongoAnswer;
import java.util.Arrays;

public class ObjectiveSortingStrategy implements SortStrategy<MongoAnswer> {
    String[] fieldOrder = {"APP", "WEB", "AI", "GAME"};

    @Override
    public int compare(MongoAnswer obj1, MongoAnswer obj2) {
        return Integer.compare(
                Arrays.asList(fieldOrder)
                        .indexOf(obj1.getQna().get("field1").toString().replace("\"", "")),
                Arrays.asList(fieldOrder)
                        .indexOf(obj2.getQna().get("field1").toString().replace("\"", "")));
    }
}
