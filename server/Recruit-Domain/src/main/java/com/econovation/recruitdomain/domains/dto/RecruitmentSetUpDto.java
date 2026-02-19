package com.econovation.recruitdomain.domains.dto;

import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecruitmentSetUpDto {
    private Integer year;

    @NotNull
    private LocalDateTime startAt;

    @NotNull
    private LocalDateTime endAt;
}
