package com.econovation.recruitdomain.domains.applicant.dto;

import com.econovation.recruitdomain.domains.applicant.domain.state.RecruitmentStates;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecruitmentStateDto {

    private RecruitmentStates states;
    private LocalDateTime startAt;

}
