package com.econovation.recruit.api.recruitment.handler;

import com.econovation.recruitdomain.domains.applicant.domain.state.RecruitmentStates;
import com.econovation.recruitdomain.domains.recruitment.domain.Recruitment;
import com.econovation.recruitdomain.domains.recruitment.event.RecruitmentEnd;
import com.econovation.recruitdomain.out.RecruitmentPort;
import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class RecruitmentEndEventHandler {

    private final RecruitmentPort recruitmentPort;

    @EventListener(RecruitmentEnd.class)
    @Transactional
    public void handle(RecruitmentEnd event){
        Long id = event.getRecruitmentId();

        recruitmentPort.findById(id)
                .ifPresentOrElse(end(), () -> log.error("id : {} 모집이 존재하지 않아 작업을 실패하였습니다.", id));
    }

    private Consumer<Recruitment> end(){
        return recruitment -> {
          recruitment.updateStates(RecruitmentStates.END);
          recruitmentPort.save(recruitment);
        };
    }

}
