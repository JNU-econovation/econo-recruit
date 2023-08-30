package com.econovation.recruitdomain.common.events.applicant;

import com.econovation.recruitdomain.common.aop.domainEvent.DomainEvent;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class SubmitApplicantEvent extends DomainEvent {
    private String applicantId;
    private String title;

    public static SubmitApplicantEvent of(String applicantId, String title) {
        return SubmitApplicantEvent.builder().applicantId(applicantId).title(title).build();
    }
}
