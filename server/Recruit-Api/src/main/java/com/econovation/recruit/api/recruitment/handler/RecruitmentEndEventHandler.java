package com.econovation.recruit.api.recruitment.handler;

import com.econovation.recruitdomain.domains.applicant.domain.state.RecruitmentStates;
import com.econovation.recruitdomain.domains.recruitment.domain.Recruitment;
import com.econovation.recruitdomain.domains.recruitment.event.RecruitmentEnd;
import com.econovation.recruitdomain.out.RecruitmentPort;
import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class RecruitmentEndEventHandler {

    private final RecruitmentPort recruitmentPort;

    @EventListener(RecruitmentEnd.class)
    @Transactional
    public void handle(RecruitmentEnd event){
        Long id = event.getRecruitmentId();

        recruitmentPort.findById(id)
                .ifPresent(end());
    }

    private Consumer<Recruitment> end(){
        return recruitment -> {
          recruitment.updateStates(RecruitmentStates.END);
        };
    }

}
