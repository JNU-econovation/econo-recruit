package com.econovation.recruitdomain.domains.recruitment.event;

import com.econovation.recruitdomain.common.aop.domainEvent.DomainEvent;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class RecruitmentRegistered extends DomainEvent {

    private final Long id;
}
