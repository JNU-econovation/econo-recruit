package com.econovation.recruit.api.recruitment.usecase;

import com.econovation.recruit.api.recruitment.command.SetUpRecruitmentCommand;
import com.econovation.recruitdomain.domains.recruitment.domain.Recruitment;

public interface RecruitmentUseCase {

    Long setUp(SetUpRecruitmentCommand command);

    Recruitment getLatestOne();

}
