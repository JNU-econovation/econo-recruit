package com.econovation.recruitdomain.domains.dto;

import java.time.LocalDateTime;
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

}
