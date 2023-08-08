package com.econovation.recruitdomain.out;


import com.econovation.recruitdomain.domains.applicant.Applicant;
import com.econovation.recruitdomain.domains.timetable.TimeTable;
import java.util.List;

public interface TimeTableRecordPort {
    //    List<TimeTableInsertDto> saveAll(List<TimeTableInsertDto> timeTableInsertDtos,Integer
    // applicantId);
    List<TimeTable> saveAll(List<TimeTable> timeTables);

    List<TimeTable> getTimeTableByApplicantId(Applicant applicant);
}
