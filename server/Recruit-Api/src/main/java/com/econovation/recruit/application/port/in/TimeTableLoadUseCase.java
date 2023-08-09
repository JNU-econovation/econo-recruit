package com.econovation.recruit.application.port.in;

import com.econovation.recruitcommon.annotation.UseCase;
import com.econovation.recruitdomain.domains.applicant.dto.TimeTableDto;
import com.econovation.recruitdomain.domains.timetable.TimeTable;
import java.util.List;
import java.util.UUID;

@UseCase
public interface TimeTableLoadUseCase {
    List<TimeTableDto> findAll();

    List<TimeTable> getTimeTableByApplicantId(UUID applicantId);
}
