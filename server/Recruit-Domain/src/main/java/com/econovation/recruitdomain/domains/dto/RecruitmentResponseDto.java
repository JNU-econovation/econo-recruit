package com.econovation.recruitdomain.domains.dto;

import com.econovation.recruitdomain.domains.recruitment.domain.Recruitment;
import com.econovation.recruitdomain.domains.recruitment.domain.RecruitmentStates;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecruitmentResponseDto {

    private Long recruitmentId;
    private int year;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private RecruitmentStates states;

    public static RecruitmentResponseDto create(Recruitment entity) {
        return new RecruitmentResponseDto(
                entity.getId(),
                entity.getYear(),
                entity.getStartAt(),
                entity.getEndAt(),
                entity.getStates());
    }
}
