package com.econovation.recruit.api.applicant.usecase;

import com.econovation.recruitcommon.annotation.UseCase;
import java.util.List;
import java.util.UUID;

@UseCase
public interface TimeTableRegisterUseCase {
    public void execute(UUID applicantId, List<Integer> timeTables);
}
