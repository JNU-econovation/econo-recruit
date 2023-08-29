package com.econovation.recruitdomain.domains.applicant.dto;

import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@Builder
public class TimeTableDto {
    private UUID applicantID;
    private List<TimeTableVo> timeTableVo;
}
