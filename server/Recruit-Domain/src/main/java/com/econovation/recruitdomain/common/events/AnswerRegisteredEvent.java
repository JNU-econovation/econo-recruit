package com.econovation.recruitdomain.common.events;

import com.econovation.recruitdomain.common.aop.domainEvent.DomainEvent;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AnswerRegisteredEvent extends DomainEvent {
    private final UUID applicantId;

    @Builder
    public AnswerRegisteredEvent(UUID applicantId) {
        this.applicantId = applicantId;
    }
}
