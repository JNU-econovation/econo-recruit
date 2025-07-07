package com.econovation.recruitdomain.domains.dto;

import java.time.LocalDateTime;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecruitmentSetUpDto {

    private Long year;
    private LocalDateTime startAt;
    private LocalDateTime endAt;

    @AssertTrue(message = "요청 바디가 잘못되었습니다.")
    public boolean validate() {
        if (year == null || startAt == null || endAt == null) return false;
        if (startAt.isBefore(LocalDateTime.now())) return false;
        if (startAt.isAfter(endAt)) return false;
        return true;
    }
}
