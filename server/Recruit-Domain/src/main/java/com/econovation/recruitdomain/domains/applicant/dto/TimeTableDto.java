package com.econovation.recruitdomain.domains.applicant.dto;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@Builder
public class TimeTableDto {
    private String applicantId;
    private List<TimeTableVo> timeTableVo;
}
