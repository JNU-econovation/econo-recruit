package com.econovation.recruitdomain.domains.recruitment.event;

import com.econovation.recruitdomain.common.aop.domainEvent.DomainEvent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RecruitmentStateUpdate extends DomainEvent {

    private final Long recruitmentId;

    // 어떤 상태로 변경할지는 핸들러에서 결정
//    private final RecruitmentStates states;

}
