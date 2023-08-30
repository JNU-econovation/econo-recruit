package com.econovation.recruitdomain.domains.applicant.event;

import com.econovation.recruitdomain.common.aop.domainEvent.DomainEvent;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class ApplicantRegisterEvent extends DomainEvent {
    private final Map<String, String> answers;

    public static ApplicantRegisterEvent of(Map<String, String> answers) {
        return ApplicantRegisterEvent.builder().answers(answers).build();
    }
}
