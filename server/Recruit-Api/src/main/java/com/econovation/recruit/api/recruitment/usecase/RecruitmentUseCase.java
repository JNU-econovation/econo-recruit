package com.econovation.recruit.api.recruitment.usecase;

import com.econovation.recruit.api.applicant.command.ChangeRecruitmentCommand;
import com.econovation.recruitdomain.domains.recruitment.domain.Recruitment;

public interface RecruitmentUseCase {

    boolean changeState(ChangeRecruitmentCommand command);

    Recruitment getLatestOne();

}
