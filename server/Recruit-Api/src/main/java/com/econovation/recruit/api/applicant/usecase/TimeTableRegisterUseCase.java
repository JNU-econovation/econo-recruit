package com.econovation.recruit.api.applicant.usecase;

import com.econovation.recruitcommon.annotation.UseCase;
import java.util.List;

@UseCase
public interface TimeTableRegisterUseCase {
    public void execute(String applicantId, List<Integer> timeTables);
}
