package com.econovation.recruit.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TimeTableInsertDto {
    private String startTime;
    private String endTime;
    private String day;
}
