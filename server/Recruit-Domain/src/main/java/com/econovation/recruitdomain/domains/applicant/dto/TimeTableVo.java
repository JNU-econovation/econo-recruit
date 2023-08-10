package com.econovation.recruitdomain.domains.applicant.dto;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class TimeTableVo {
    private Integer startTime;
    private Integer endTime;
}
