package com.econovation.recruit.api.recruitment.command;

import java.time.LocalDateTime;
import javax.validation.constraints.AssertTrue;
import lombok.Getter;

@Getter
public class SetUpRecruitmentCommand {
    private final Long year;
    private final LocalDateTime startAt;
    private final LocalDateTime endAt;

    public SetUpRecruitmentCommand(Long year, LocalDateTime startAt, LocalDateTime endAt) {
        this.startAt = startAt;
        this.endAt = endAt;
        this.year = year;
    }

    @AssertTrue(message = "요청 바디가 잘못되었습니다.")
    public boolean validate() {
        if (year == null || startAt == null || endAt == null) return false;
        if (startAt.isBefore(LocalDateTime.now())) return false;
        if (startAt.isAfter(endAt)) return false;
        return true;
    }
}
