package com.econovation.recruit.utils.sort.strategy;

import com.econovation.recruitdomain.domains.applicant.domain.MongoAnswer;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import org.aspectj.util.Reflection;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

// @Component
@Qualifier("answerObjectiveSort")
public class AnswerObjectiveSortingStrategy implements SortStrategy<MongoAnswer> {
    String[] fieldOrder = {"APP", "WEB", "AI", "GAME"};

    @Override
    public int compare(MongoAnswer obj1, MongoAnswer obj2) {
        return Integer.compare(
                Arrays.asList(fieldOrder).indexOf(obj1.getQna().get("field1").toString()),
                Arrays.asList(fieldOrder).indexOf(obj2.getQna().get("field1").toString()));
    }
}
