package com.econovation.recruit.api.applicant.command;

import com.econovation.recruitdomain.domains.applicant.domain.state.RecruitmentStates;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class ChangeRecruitmentCommand {

    private final RecruitmentStates states;
    private final  LocalDateTime at;

    public ChangeRecruitmentCommand(RecruitmentStates states, LocalDateTime at){
        if(RecruitmentStates.START == states){
            // 현재 시간보다 앞 서 있으면 안됨
            if(LocalDateTime.now().isAfter(at))
                throw new IllegalArgumentException("Recruitment 시작 시간은 현재 시간보다 앞에 있을 수 없습니다.");
        }

        this.at = at;
        this.states = states;
    }

}
