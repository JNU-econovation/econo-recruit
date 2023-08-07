package com.econovation.recruit.application.port.in;


import com.econovation.recruitdomain.domain.dto.TimeTableInsertDto;
import com.econovation.recruitdomain.domain.timetable.TimeTable;
import java.util.List;

public interface TimeTableUseCase {
    //    List<TimeTableInsertDto> submitTimeTable(HashMap<String, Object> param, Integer
    // applicantId);
    //    List<TimeTable> submitTimeTable(List<TimeTableInsertDto> timetable);
    List<TimeTable> submitTimeTable(List<TimeTableInsertDto> timetable, Integer applicantId);

    List<TimeTable> getTimeTableByApplicantId(Integer applicantId);

    List<TimeTable> findAll();
}
