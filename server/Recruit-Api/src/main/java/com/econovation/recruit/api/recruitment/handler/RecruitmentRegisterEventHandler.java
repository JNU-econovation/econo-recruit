package com.econovation.recruit.api.recruitment.handler;

import com.econovation.recruit.api.recruitment.util.LatestRecruitmentVo;
import com.econovation.recruit.api.recruitment.util.RecruitmentScheduler;
import com.econovation.recruitdomain.domains.recruitment.domain.Recruitment;
import com.econovation.recruitdomain.domains.recruitment.event.RecruitmentRegister;
import com.econovation.recruitdomain.out.RecruitmentPort;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class RecruitmentRegisterEventHandler {

    private final RecruitmentPort recruitmentPort;
    private final RecruitmentScheduler recruitmentScheduler;
    private final LatestRecruitmentVo latestRecruitment;

    @Transactional
    @EventListener(RecruitmentRegister.class)
    public void handle(RecruitmentRegister event){
        // NON_START 상태를, startAt 시간이 되면 RECRUITING 상태로 변경하는 작업 예약
        // RECRUITING 상태를, endAt 시간이 되면, END 상태로 변경하는 작업 예약
        recruitmentScheduler.reserveEvent(event.getId());

        // 전역 VO 변수 갱신
        recruitmentPort.findLatestOne()
                .ifPresentOrElse(latestRecruitment::refreshRecruitment,
                        ()->log.error("recruitmentVo 갱신 실패"));
    }

}
