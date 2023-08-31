package com.econovation.recruitdomain.common.events;

import com.econovation.recruitdomain.common.aop.domainEvent.DomainEvent;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApplicantSubmitEvent extends DomainEvent {
    private String applicantId;
    private String title;

    public static ApplicantSubmitEvent of(String applicantId, String title) {
        return ApplicantSubmitEvent.builder().applicantId(applicantId).title(title).build();
    }
}
