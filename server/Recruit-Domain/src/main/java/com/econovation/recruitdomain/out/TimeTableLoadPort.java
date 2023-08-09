package com.econovation.recruitdomain.out;

import com.econovation.recruitdomain.domains.timetable.TimeTable;
import java.util.List;
import java.util.UUID;

public interface TimeTableLoadPort {
    List<TimeTable> getTimeTableByApplicantId(UUID applicantId);

    List<TimeTable> findAll();
}
