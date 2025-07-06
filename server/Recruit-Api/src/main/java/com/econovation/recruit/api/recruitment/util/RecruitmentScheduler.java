package com.econovation.recruit.api.recruitment.util;

import com.econovation.recruitdomain.common.aop.domainEvent.Events;
import com.econovation.recruitdomain.domains.recruitment.domain.Recruitment;
import com.econovation.recruitdomain.domains.recruitment.event.RecruitmentEnd;
import com.econovation.recruitdomain.domains.recruitment.event.RecruitmentStart;
import com.econovation.recruitdomain.out.RecruitmentPort;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RecruitmentScheduler {

    private static final ZoneId KST = ZoneId.of("Asia/Seoul");

    private final RecruitmentPort recruitmentPort;
    private final TaskScheduler taskScheduler;

    // TODO: DB에서 예약된 작업 불러오기
    public void loadNonStartedJob(){}

    public void reserveEvent(Recruitment recruitment){
        reserveStart(recruitment);
        reserveEnd(recruitment);
    }

    private void reserveStart(Recruitment target){
        ZonedDateTime zonedStartAt = ZonedDateTime.of(target.getStartAt(), KST);

        taskScheduler.schedule(() -> {
            Events.raise(new RecruitmentStart(target.getId()));
        }, zonedStartAt.toInstant());
    }

    private void reserveEnd(Recruitment target){
        ZonedDateTime zonedEndAt = ZonedDateTime.of(target.getEndAt(), KST);

        taskScheduler.schedule(() -> {
            Events.raise(new RecruitmentEnd(target.getId()));
        }, zonedEndAt.toInstant());
    }



}
