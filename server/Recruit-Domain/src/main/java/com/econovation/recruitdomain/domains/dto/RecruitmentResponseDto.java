package com.econovation.recruitdomain.domains.dto;

import com.econovation.recruitdomain.domains.applicant.domain.state.RecruitmentStates;
import com.econovation.recruitdomain.domains.recruitment.domain.Recruitment;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecruitmentResponseDto {

    private Long recruitmentId;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private RecruitmentStates states;

    public static RecruitmentResponseDto create(Recruitment entity) {
        return new RecruitmentResponseDto(
                entity.getId(), entity.getStartAt(), entity.getEndAt(), entity.getStates());
    }
}
