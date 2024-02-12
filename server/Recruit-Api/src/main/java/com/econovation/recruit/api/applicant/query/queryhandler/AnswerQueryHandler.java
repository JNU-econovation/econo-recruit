package com.econovation.recruit.api.applicant.query.queryhandler;

import com.econovation.recruit.api.applicant.aggregate.AnswerAggregate;
import com.econovation.recruit.api.applicant.query.AnswerQuery;
import com.econovation.recruitdomain.domains.applicant.domain.MongoAnswer;
import com.econovation.recruitdomain.domains.applicant.domain.MongoAnswerAdaptor;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AnswerQueryHandler {
    private final MongoAnswerAdaptor answerAdaptor;

    @QueryHandler
    public AnswerAggregate handle(AnswerQuery query) {
        MongoAnswer result =
                answerAdaptor
                        .findById(query.getAnswerId())
                        .fold(
                                (exception) -> {
                                    throw exception;
                                },
                                (answer) -> {
                                    return answer;
                                });
        return new AnswerAggregate(result.getId(), result.getYear(), result.getQna());
    }
}
