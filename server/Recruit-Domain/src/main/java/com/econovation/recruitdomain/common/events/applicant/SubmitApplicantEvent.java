package com.econovation.recruitdomain.common.events.applicant;

import com.econovation.recruitdomain.common.aop.domainEvent.DomainEvent;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class SubmitApplicantEvent extends DomainEvent {
    private UUID applicantId;
    public static SubmitApplicantEvent from(UUID applicantId) {
        return SubmitApplicantEvent.builder().applicantId(applicantId).build();
    }
}
