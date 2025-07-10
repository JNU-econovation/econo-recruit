package com.econovation.recruitdomain.domains.dto;

import javax.validation.constraints.AssertTrue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecruitmentSetUpDto {

    private Long year;
    private Long startAt;
    private Long endAt;

    @AssertTrue(message = "요청 바디가 잘못되었습니다.")
    public boolean validate() {
        if (year == null || startAt == null || endAt == null) return false;
        if (System.currentTimeMillis() > startAt) return false;
        if (endAt < startAt) return false;

        return true;
    }
}
