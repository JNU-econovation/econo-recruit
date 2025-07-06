package com.econovation.recruitdomain.domains.recruitment.event;

import com.econovation.recruitdomain.common.aop.domainEvent.DomainEvent;
import com.econovation.recruitdomain.domains.recruitment.domain.Recruitment;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class RecruitmentRegister extends DomainEvent {

    private final Long id;

    public static RecruitmentRegister from(Recruitment entity){
        return RecruitmentRegister.builder()
                .id(entity.getId())
                .build();
    }

}
