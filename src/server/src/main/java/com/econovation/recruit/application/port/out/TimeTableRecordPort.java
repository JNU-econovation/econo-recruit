package com.econovation.recruit.application.port.out;

import com.econovation.recruit.domain.dto.TimeTableInsertDto;

import java.util.List;

public interface TimeTableRecordPort {
    List<TimeTableInsertDto> saveAll(List<TimeTableInsertDto> timeTableInsertDtos,Integer applicantId);
}
