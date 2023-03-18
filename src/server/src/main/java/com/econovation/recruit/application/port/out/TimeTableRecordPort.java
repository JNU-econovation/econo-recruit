package com.econovation.recruit.application.port.out;

import com.econovation.recruit.domain.applicant.Applicant;
import com.econovation.recruit.domain.timetable.TimeTable;

import java.util.List;

public interface TimeTableRecordPort {
//    List<TimeTableInsertDto> saveAll(List<TimeTableInsertDto> timeTableInsertDtos,Integer applicantId);
    List<TimeTable> saveAll(List<TimeTable> timeTables);

    List<TimeTable> getTimeTableByApplicantId(Applicant applicant);
}
