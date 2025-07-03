package com.econovation.recruit.api.recruitment.usecase;

import com.econovation.recruit.api.applicant.command.ChangeRecruitmentCommand;

public interface RecruitmentUseCase {

    boolean changeState(ChangeRecruitmentCommand command);

}
