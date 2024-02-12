package com.econovation.recruitdomain.domains.applicant.event.domainevent;

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
    private final String email;

    public static ApplicantRegisterEvent of(
            String applicantId, String userName, String hopeField, String email) {
        return ApplicantRegisterEvent.builder()
                .applicantId(applicantId)
                .hopeField(hopeField)
                .userName(userName)
                .email(email)
                .build();
    }
}
