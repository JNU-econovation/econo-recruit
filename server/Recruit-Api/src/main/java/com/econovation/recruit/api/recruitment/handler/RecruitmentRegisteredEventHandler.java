package com.econovation.recruit.api.recruitment.handler;

import com.econovation.recruit.api.recruitment.util.LatestRecruitmentVo;
import com.econovation.recruit.api.recruitment.quartz.RecruitmentScheduler;
import com.econovation.recruitdomain.domains.recruitment.event.RecruitmentRegistered;
import com.econovation.recruitdomain.out.RecruitmentPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class RecruitmentRegisteredEventHandler {

    private final RecruitmentPort recruitmentPort;
    private final RecruitmentScheduler recruitmentScheduler;
    private final LatestRecruitmentVo latestRecruitment;

    @Transactional
    @EventListener(RecruitmentRegistered.class)
    public void handle(RecruitmentRegistered event) {

        // 1. 전역 상태 최신화
        // 2. NON_START 상태를, startAt 시간이 되면 RECRUITING 상태로 변경하는 작업 예약
        // 3. RECRUITING 상태를, endAt 시간이 되면, END 상태로 변경하는 작업 예약
        recruitmentPort.findById(event.getId())
                .ifPresent(recruitment -> {
                    latestRecruitment.refreshRecruitment(recruitment);
                    recruitmentScheduler.reserveStart(recruitment);
                    recruitmentScheduler.reserveEnd(recruitment);
                });

    }
}
