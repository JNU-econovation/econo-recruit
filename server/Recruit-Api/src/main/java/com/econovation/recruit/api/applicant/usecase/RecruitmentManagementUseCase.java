package com.econovation.recruit.api.applicant.usecase;

import com.econovation.recruit.api.applicant.command.ChangeRecruitmentCommand;

public interface RecruitmentManagementUseCase {

    boolean changeState(ChangeRecruitmentCommand command);

}
