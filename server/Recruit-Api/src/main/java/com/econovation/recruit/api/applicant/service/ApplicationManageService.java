package com.econovation.recruit.api.applicant.service;

import com.econovation.recruit.api.applicant.command.ChangeRecruitmentCommand;
import com.econovation.recruit.api.applicant.usecase.RecruitmentManagementUseCase;
import com.econovation.recruitdomain.domains.applicant.domain.state.RecruitmentStates;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationManageService implements RecruitmentManagementUseCase {

    @Override
    public boolean changeState(ChangeRecruitmentCommand command) {



        return false;
    }
}
