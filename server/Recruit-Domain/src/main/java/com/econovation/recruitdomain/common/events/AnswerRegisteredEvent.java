package com.econovation.recruitdomain.common.events;

import com.econovation.recruitdomain.common.aop.domainEvent.DomainEvent;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AnswerRegisteredEvent extends DomainEvent {
    private final String applicantId;

    @Builder
    public AnswerRegisteredEvent(String applicantId) {
        this.applicantId = applicantId;
    }
}
