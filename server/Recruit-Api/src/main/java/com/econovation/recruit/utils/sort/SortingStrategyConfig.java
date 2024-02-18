package com.econovation.recruit.utils.sort;

import com.econovation.recruit.utils.sort.strategy.AnswerNameAscendingSortingStrategy;
import com.econovation.recruit.utils.sort.strategy.AnswerNewestSortingStrategy;
import com.econovation.recruit.utils.sort.strategy.AnswerObjectiveSortingStrategy;
import com.econovation.recruit.utils.sort.strategy.InterviewerNameAscendingSortingSortingStrategy;
import com.econovation.recruit.utils.sort.strategy.InterviewerNewestSortingStrategy;
import com.econovation.recruit.utils.sort.strategy.SortStrategy;
import com.econovation.recruitdomain.domains.applicant.domain.MongoAnswer;
import com.econovation.recruitdomain.domains.interviewer.domain.Interviewer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SortingStrategyConfig {
    @Bean
    @Qualifier(value = "interviewerNewestSort")
    public SortStrategy<Interviewer> interviewerNewestSortingStrategy() {
        return new InterviewerNewestSortingStrategy();
    }

    @Bean
    @Qualifier(value = "interviewerNameSort")
    public SortStrategy<Interviewer> interviewerNameAscendingSortingSortingStrategy() {
        return new InterviewerNameAscendingSortingSortingStrategy();
    }

    @Bean
    @Qualifier(value = "answerNewestSort")
    public SortStrategy<MongoAnswer> answerNewestSortingStrategy() {
        return new AnswerNewestSortingStrategy();
    }

    @Bean
    @Qualifier(value = "answerNameSort")
    public SortStrategy<MongoAnswer> answerNameAscendingSortingStrategy() {
        return new AnswerNameAscendingSortingStrategy();
    }

    @Bean
    @Qualifier(value = "answerObjectiveSort")
    public SortStrategy<MongoAnswer> answerObjectiveSortingStrategy() {
        return new AnswerObjectiveSortingStrategy();
    }
}
