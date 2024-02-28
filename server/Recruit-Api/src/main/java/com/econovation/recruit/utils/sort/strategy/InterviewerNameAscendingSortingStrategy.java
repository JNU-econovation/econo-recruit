package com.econovation.recruit.utils.sort.strategy;

import com.econovation.recruitdomain.domains.interviewer.domain.Interviewer;
import org.springframework.beans.factory.annotation.Qualifier;

// @Component
@Qualifier("interviewerNameSort")
public class InterviewerNameAscendingSortingStrategy implements SortStrategy<Interviewer> {

    @Override
    public int compare(Interviewer obj1, Interviewer obj2) {
        return obj1.getName().compareTo(obj2.getName());
    }
}
