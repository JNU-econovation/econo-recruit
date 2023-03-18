package com.econovation.recruit.application.port.out;

import com.econovation.recruit.domain.applicant.Applicant;
import com.econovation.recruit.domain.timetable.TimeTable;

import java.util.List;

public interface TimeTableLoadPort {
    List<TimeTable> getTimeTableByApplicantId(Applicant applicant);

    List<TimeTable> findAll();
}
