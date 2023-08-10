package com.econovation.recruitdomain.domains.applicant.dto;

import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TimeTableDto {
    private UUID applicantID;
    private List<TimeTableVo> timeTableVo;
}
