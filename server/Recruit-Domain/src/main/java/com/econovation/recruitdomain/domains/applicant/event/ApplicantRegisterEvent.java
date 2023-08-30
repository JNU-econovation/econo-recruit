package com.econovation.recruitdomain.domains.applicant.event;

import com.econovation.recruitdomain.common.aop.domainEvent.DomainEvent;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class ApplicantRegisterEvent extends DomainEvent {
    private final String applicantId;
    private final String hopeField;
    private final String userName;

    public static ApplicantRegisterEvent of(String applicantId, String userName, String hopeField) {
        return ApplicantRegisterEvent.builder().hopeField(hopeField).userName(userName).build();
    }
}
