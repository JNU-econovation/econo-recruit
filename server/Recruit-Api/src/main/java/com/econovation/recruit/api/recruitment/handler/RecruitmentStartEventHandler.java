package com.econovation.recruit.api.recruitment.handler;

import com.econovation.recruitdomain.domains.applicant.domain.state.RecruitmentStates;
import com.econovation.recruitdomain.domains.recruitment.domain.Recruitment;
import com.econovation.recruitdomain.domains.recruitment.event.RecruitmentEnd;
import com.econovation.recruitdomain.domains.recruitment.event.RecruitmentStart;
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
public class RecruitmentStartEventHandler {

    private final RecruitmentPort recruitmentPort;

    @EventListener(RecruitmentStart.class)
    @Transactional
    public void handle(RecruitmentStart event){
        Long id = event.getRecruitmentId();

        recruitmentPort.findById(id)
                .ifPresentOrElse(start(),  () -> log.error("id : {} 모집이 존재하지 않아 작업을 실패하였습니다.", id));
    }

    private Consumer<Recruitment> start(){
        return recruitment -> {
            recruitment.updateStates(RecruitmentStates.RECRUITING);
            recruitmentPort.save(recruitment);
        };
    }

}
