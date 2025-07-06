package com.econovation.recruit.api.recruitment.service;

import com.econovation.recruit.api.recruitment.command.SetUpRecruitmentCommand;
import com.econovation.recruit.api.recruitment.usecase.RecruitmentUseCase;
import com.econovation.recruitdomain.domains.recruitment.domain.Recruitment;
import com.econovation.recruitdomain.out.RecruitmentPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecruitmentService implements RecruitmentUseCase {

    private final RecruitmentPort recruitmentPort;

    @Override
    public boolean setUp(SetUpRecruitmentCommand command){
        // 상태 변경 예약
        // DelayQueue 활용
        Recruitment recruitment = Recruitment.builder()
                .endAt(command.getEndAt())
                .startAt(command.getStartAt())
                .year(command.getYear())
                .build();


        return false;
    }

    @Override
    public Recruitment getLatestOne() {


        return null;
    }
}
