package com.econovation.recruit.api.recruitment.util;

import com.econovation.recruit.api.recruitment.task.RecruitmentJob;
import com.econovation.recruitdomain.domains.applicant.domain.state.RecruitmentStates;
import com.econovation.recruitdomain.domains.recruitment.domain.Recruitment;
import com.econovation.recruitdomain.out.RecruitmentPort;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RecruitmentScheduler {

    private static final ZoneId KST = ZoneId.of("Asia/Seoul");

    private final RecruitmentPort recruitmentPort;
    private final TaskScheduler taskScheduler;

    // DB에서 예약된 작업 불러오기
    @PostConstruct
    public void loadNonStartedJob(){
        List<Recruitment> startJobs = recruitmentPort.findByStates(RecruitmentStates.NON_START);
        List<Recruitment> endJobs = recruitmentPort.findByStates(RecruitmentStates.RECRUITING);

        startJobs.forEach(startJob -> {
            this.reserveStart(startJob);
            this.reserveEnd(startJob);
        });

        endJobs.forEach(this::reserveEnd);
    }

    public void reserveEvent(Long id){
        Optional<Recruitment> saved = recruitmentPort.findById(id);

        saved.ifPresent((recruitment)->{
            reserveStart(recruitment);
            reserveEnd(recruitment);
        });
    }

    private void reserveStart(Recruitment target){
        ZonedDateTime zonedStartAt = ZonedDateTime.of(target.getStartAt(), KST);

        taskScheduler.schedule(() -> {
            target.updateStates(RecruitmentStates.RECRUITING);
            recruitmentPort.save(target);
        }, zonedStartAt.toInstant());
    }

    private void reserveEnd(Recruitment target){
        ZonedDateTime zonedEndAt = ZonedDateTime.of(target.getEndAt(), KST);

        taskScheduler.schedule(() -> {
            target.updateStates(RecruitmentStates.END);
            recruitmentPort.save(target);
        }, zonedEndAt.toInstant());
    }



}
