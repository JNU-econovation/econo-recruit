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
    private String title;

    public static SubmitApplicantEvent of(UUID applicantId, String title) {
        return SubmitApplicantEvent.builder().applicantId(applicantId).title(title).build();
    }
}
