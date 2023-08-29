package com.econovation.recruitdomain.out;

import com.econovation.recruitdomain.domains.timetable.domain.TimeTable;
import java.util.List;

public interface TimeTableLoadPort {
    List<TimeTable> getTimeTableByApplicantId(String applicantId);

    List<TimeTable> findAll();
}
