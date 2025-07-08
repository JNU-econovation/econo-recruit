package com.econovation.recruitdomain.domains.dto;

import com.econovation.recruitdomain.domains.applicant.domain.state.RecruitmentStates;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class RecruitmentResponseDto {

    private Long recruitmentId;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private RecruitmentStates states;

}
