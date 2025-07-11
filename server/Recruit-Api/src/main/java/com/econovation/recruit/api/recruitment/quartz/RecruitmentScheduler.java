package com.econovation.recruit.api.recruitment.quartz;

import com.econovation.recruit.api.recruitment.quartz.exception.QuartzException;
import com.econovation.recruitdomain.domains.recruitment.domain.Recruitment;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RecruitmentScheduler {

    private static final ZoneId KST = ZoneId.of("Asia/Seoul");

    private final Scheduler scheduler;

    // DB에서 예약된 작업 불러오기
//    @PostConstruct
//    public void loadNonStartedJob() {
//        List<Recruitment> startJobs = recruitmentPort.findByStates(RecruitmentStates.NON_START);
//        List<Recruitment> endJobs = recruitmentPort.findByStates(RecruitmentStates.RECRUITING);
//
//        startJobs.forEach(
//                startJob -> {
//                    this.reserveStart(startJob);
//                    this.reserveEnd(startJob);
//                });
//
//        endJobs.forEach(this::reserveEnd);
//    }

    public void reserveStart(Recruitment target) {
        try {
            JobDetail jobDetail = RecruitmentJob.getStartJob(target.getId(), target.getYear());

            scheduler.scheduleJob(jobDetail, RecruitmentTrigger.get(jobDetail.getKey(), startAt(target)));

        } catch (SchedulerException e){
            throw new QuartzException(e);
        }
    }

    public void reserveEnd(Recruitment target) {
        try {
            JobDetail jobDetail = RecruitmentJob.getEndJob(target.getId(), target.getYear());

            scheduler.scheduleJob(jobDetail, RecruitmentTrigger.get(jobDetail.getKey(), endAt(target)));

        } catch (SchedulerException e){
            throw new QuartzException(e);
        }
    }

    public void cancelJob(Long recruitmentId){
        try {
            scheduler.deleteJob(new JobKey(recruitmentId.toString()));
        } catch (SchedulerException e){
            throw new QuartzException(e);
        }
    }

    private ZonedDateTime startAt(Recruitment recruitment){
        return ZonedDateTime.of(recruitment.getStartAt(), KST);
    }

    private ZonedDateTime endAt(Recruitment recruitment){
        return ZonedDateTime.of(recruitment.getEndAt(), KST);
    }

}
