package com.econovation.recruitdomain.domains.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecruitmentSetUpDto {

    private Integer year;
    private Long startAt;
    private Long endAt;
}
