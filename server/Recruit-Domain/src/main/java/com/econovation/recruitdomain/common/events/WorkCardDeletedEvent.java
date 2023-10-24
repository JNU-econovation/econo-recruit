package com.econovation.recruitdomain.common.events;

import com.econovation.recruitdomain.common.aop.domainEvent.DomainEvent;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class WorkCardDeletedEvent extends DomainEvent {
    private final Long cardId;

    @Builder
    public WorkCardDeletedEvent(Long cardId) {
        this.cardId = cardId;
    }

    public static WorkCardDeletedEvent of(Long cardId) {
        return WorkCardDeletedEvent.builder().cardId(cardId).build();
    }
}
