package com.econovation.recruit.api.recruitment.service;

import com.econovation.recruit.api.applicant.command.ChangeRecruitmentCommand;
import com.econovation.recruit.api.recruitment.usecase.RecruitmentUseCase;
import com.econovation.recruit.api.recruitment.util.RecruitmentCommandStore;
import com.econovation.recruitdomain.domains.recruitment.domain.Recruitment;
import com.econovation.recruitdomain.out.RecruitmentPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecruitmentService implements RecruitmentUseCase {

    private final RecruitmentPort recruitmentPort;
    private final RecruitmentCommandStore commandStore;

    @Override
    public boolean changeState(ChangeRecruitmentCommand command){
        // 상태 변경 예약
        // DelayQueue 활용



        return false;
    }

    @Override
    public Recruitment getLatestOne() {


        return null;
    }
}
