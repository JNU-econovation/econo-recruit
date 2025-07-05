package com.econovation.recruit.api.applicant.command;

import com.econovation.recruitdomain.domains.applicant.domain.state.RecruitmentStates;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class ChangeRecruitmentCommand {

    private final RecruitmentStates states;
    private final Long year;
    private final LocalDateTime at;

    public ChangeRecruitmentCommand(RecruitmentStates states, Long year,LocalDateTime at){
        this.at = at;
        this.states = states;
        this.year = year;
    }

}
