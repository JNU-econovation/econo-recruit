package com.econovation.recruit.api.recruitment.handler;

import com.econovation.recruit.api.recruitment.util.RecruitmentScheduler;
import com.econovation.recruitdomain.domains.applicant.domain.state.RecruitmentStates;
import com.econovation.recruitdomain.domains.recruitment.event.RecruitmentTerminated;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class RecruitmentTerminatedEventHandler {

    private final RecruitmentScheduler recruitmentScheduler;

    @EventListener(RecruitmentTerminated.class)
    @Transactional
    public void handle(RecruitmentTerminated event){
        recruitmentScheduler.cancelJob(event.getId(), RecruitmentStates.RECRUITING);
        recruitmentScheduler.cancelJob(event.getId(), RecruitmentStates.END);
    }

}
