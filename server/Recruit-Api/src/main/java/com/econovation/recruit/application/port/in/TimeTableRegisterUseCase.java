package com.econovation.recruit.application.port.in;

import com.econovation.recruitcommon.annotation.UseCase;
import com.econovation.recruitdomain.domains.applicant.dto.TimeTableVo;
import java.util.List;
import java.util.UUID;

@UseCase
public interface TimeTableRegisterUseCase {
    public void execute(UUID applicantId, List<TimeTableVo> timeTables);
}
