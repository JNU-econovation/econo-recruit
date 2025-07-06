package com.econovation.recruit.api.recruitment.service;

import com.econovation.recruit.api.recruitment.command.SetUpRecruitmentCommand;
import com.econovation.recruit.api.recruitment.usecase.RecruitmentUseCase;
import com.econovation.recruitdomain.domains.applicant.domain.state.RecruitmentStates;
import com.econovation.recruitdomain.domains.recruitment.domain.Recruitment;
import com.econovation.recruitdomain.out.RecruitmentPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecruitmentService implements RecruitmentUseCase {

    private final RecruitmentPort recruitmentPort;

    @Override
    public Long setUp(SetUpRecruitmentCommand command){
        // 상태 변경 예약
        // DelayQueue 활용
        Recruitment recruitment = Recruitment.builder()
                .endAt(command.getEndAt())
                .startAt(command.getStartAt())
                .year(command.getYear())
                .states(RecruitmentStates.NON_START)
                .build();

        Recruitment saved = recruitmentPort.save(recruitment);

        // NON_START 상태를, startAt 시간이 되면 RECRUITING 상태로 변경하는 작업 예약
        // RECRUITING 상태를, endAt 시간이 되면, END 상태로 변경하는 작업 예약

        return saved.getId();
    }

    @Override
    public Recruitment getLatestOne() {
        return recruitmentPort.findLatestOne()
                .orElseThrow(() -> new IllegalArgumentException("최신의 모집이 존재하지 않습니다."));
    }
}
