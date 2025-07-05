package com.econovation.recruitdomain.domains.applicant.dto;

import com.econovation.recruitdomain.domains.applicant.domain.state.RecruitmentStates;
import java.time.LocalDateTime;
import javax.validation.constraints.AssertTrue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecruitmentStateDto {

    private RecruitmentStates states;
    private Long year;
    private LocalDateTime reservedAt;

    @AssertTrue(message = "요청 바디가 잘못되었습니다.")
    public boolean validate(){
        if(reservedAt.isBefore(LocalDateTime.now())) return false;
        if(states == null) return false;
        return true;
    }


}
