package com.econovation.recruit.api.recruitment.handler;

import com.econovation.recruit.api.recruitment.quartz.RecruitmentScheduler;
import com.econovation.recruit.api.recruitment.util.LatestRecruitmentVo;
import com.econovation.recruitdomain.domains.recruitment.event.RecruitmentTerminated;
import com.econovation.recruitdomain.out.RecruitmentPort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class RecruitmentTerminatedEventHandler {

    private final RecruitmentScheduler recruitmentScheduler;
    private final LatestRecruitmentVo latestRecruitmentVo;
    private final RecruitmentPort recruitmentPort;

    @EventListener(RecruitmentTerminated.class)
    @Transactional
    public void handle(RecruitmentTerminated event){
        // 작업 예약 취소
        // 삭제 대상이 이미 진행 중인 모집이라면 service가 이미 삭제했다.
        recruitmentScheduler.cancelJob(event.getId());
        // 전역 변수 최신화
        recruitmentPort.findById(event.getId())
                .ifPresent(latestRecruitmentVo::refreshRecruitment);
    }

}
