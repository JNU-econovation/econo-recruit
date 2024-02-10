package com.econovation.recruit.api.applicant.aggregate;

import com.econovation.recruitdomain.domains.applicant.domain.MongoAnswer;
import com.econovation.recruitdomain.domains.applicant.domain.MongoAnswerAdaptor;
import com.econovation.recruitdomain.domains.applicant.event.aggregateevent.AnswerCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AnswerCreatedEventListener {
    private final MongoAnswerAdaptor answerAdaptor;

    @EventHandler
    public void handle(AnswerCreatedEvent event) {
        MongoAnswer answer =
                MongoAnswer.builder()
                        .id(event.getId())
                        .year(event.getYear())
                        .qna(event.getQna())
                        .build();
        answerAdaptor.save(answer);
    }
}
