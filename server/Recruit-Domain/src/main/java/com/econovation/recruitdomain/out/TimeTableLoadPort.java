package com.econovation.recruitdomain.out;


import com.econovation.recruitdomain.domain.applicant.Applicant;
import com.econovation.recruitdomain.domain.timetable.TimeTable;
import java.util.List;

public interface TimeTableLoadPort {
    List<TimeTable> getTimeTableByApplicantId(Applicant applicant);

    List<TimeTable> findAll();
}
