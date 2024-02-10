package com.econovation.recruit.api.applicant.aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import com.econovation.recruit.api.applicant.command.CreateAnswerCommand;
import com.econovation.recruitdomain.domains.applicant.event.aggregateevent.AnswerCreatedEvent;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Aggregate
@Slf4j
public class AnswerAggregate {

    @AggregateIdentifier private String id;
    private Integer year;
    private Map<String, Object> qna;
    // Constructor for creating an AnswerAggregate
    @CommandHandler
    public AnswerAggregate(CreateAnswerCommand command) {
        apply(new AnswerCreatedEvent(command.getId(), command.getYear(), command.getQna()));
    }

    // Event handler for AnswerCreatedEvent
    @EventSourcingHandler
    public void on(AnswerCreatedEvent event) {
        this.id = event.getId();
        this.year = event.getYear();
        this.qna = event.getQna();
    }
}
