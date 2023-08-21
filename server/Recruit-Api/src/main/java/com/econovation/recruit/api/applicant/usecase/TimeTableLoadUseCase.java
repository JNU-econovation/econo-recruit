package com.econovation.recruit.api.applicant.usecase;

import com.econovation.recruitcommon.annotation.UseCase;
import com.econovation.recruitdomain.domains.applicant.dto.TimeTableDto;
import com.econovation.recruitdomain.domains.timetable.domain.TimeTable;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@UseCase
public interface TimeTableLoadUseCase {
    List<TimeTableDto> findAll();

    List<TimeTable> getTimeTableByApplicantId(UUID applicantId);

    Map<Integer, Map<String, String>> findAllSimpleApplicantWithTimeTable();
}
