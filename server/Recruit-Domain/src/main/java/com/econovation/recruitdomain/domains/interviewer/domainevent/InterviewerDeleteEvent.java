package com.econovation.recruitdomain.domains.interviewer.domainevent;

import com.econovation.recruitdomain.common.aop.domainEvent.DomainEvent;
import com.econovation.recruitdomain.domains.interviewer.domain.Interviewer;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class InterviewerDeleteEvent extends DomainEvent {
    private final Interviewer interviewer;

    public static InterviewerDeleteEvent of(Interviewer interviewer) {
        return InterviewerDeleteEvent.builder().interviewer(interviewer).build();
    }
}
