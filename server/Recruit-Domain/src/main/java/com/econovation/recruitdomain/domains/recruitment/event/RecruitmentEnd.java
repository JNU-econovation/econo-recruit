package com.econovation.recruitdomain.domains.recruitment.event;

import com.econovation.recruitdomain.common.aop.domainEvent.DomainEvent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RecruitmentEnd extends DomainEvent {

    private final Long recruitmentId;

}
