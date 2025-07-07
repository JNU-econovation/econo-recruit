package com.econovation.recruit.api.recruitment.util;

import com.econovation.recruitdomain.domains.applicant.domain.state.RecruitmentStates;
import com.econovation.recruitdomain.domains.recruitment.domain.Recruitment;
import com.econovation.recruitdomain.out.RecruitmentPort;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class RecruitmentScheduler {

    private static final ZoneId KST = ZoneId.of("Asia/Seoul");

    private final Map<String, Future<?>> jobStore = new ConcurrentHashMap<>();

    private final RecruitmentPort recruitmentPort;
    private final TaskScheduler taskScheduler;

    // DB에서 예약된 작업 불러오기
    @PostConstruct
    public void loadNonStartedJob() {
        List<Recruitment> startJobs = recruitmentPort.findByStates(RecruitmentStates.NON_START);
        List<Recruitment> endJobs = recruitmentPort.findByStates(RecruitmentStates.RECRUITING);

        startJobs.forEach(
                startJob -> {
                    this.reserveStart(startJob);
                    this.reserveEnd(startJob);
                });

        endJobs.forEach(this::reserveEnd);
    }

    public void reserveEvent(Long id) {
        Optional<Recruitment> saved = recruitmentPort.findById(id);

        saved.ifPresent(
                (recruitment) -> {
                    reserveStart(recruitment);
                    reserveEnd(recruitment);
                });
    }

    public void cancelJob(Long id, RecruitmentStates states){
        Optional<Recruitment> saved = recruitmentPort.findById(id);
        saved.ifPresentOrElse(r->this.cancelJob(id,states),
                ()->log.error("recruitment가 존재하지 않음 : {}", id));
    }

    private void reserveStart(Recruitment target) {
        ZonedDateTime zonedStartAt = ZonedDateTime.of(target.getStartAt(), KST);
        String key = createKey(target, RecruitmentStates.RECRUITING);

        Future<?> future = taskScheduler.schedule(
                () -> {
                    target.updateStates(RecruitmentStates.RECRUITING);
                    recruitmentPort.save(target);
                    remove(key);
                },
                zonedStartAt.toInstant());

        store(key, future);
    }

    private void reserveEnd(Recruitment target) {
        ZonedDateTime zonedEndAt = ZonedDateTime.of(target.getEndAt(), KST);
        String key = createKey(target, RecruitmentStates.END);

        Future<?> future = taskScheduler.schedule(
                () -> {
                    target.updateStates(RecruitmentStates.END);
                    recruitmentPort.save(target);
                    remove(key);
                },
                zonedEndAt.toInstant());

        store(key, future);
    }

    private void cancel(String key){
        if(!jobStore.containsKey(key)){
            log.error("해당 작업이 존재하지 않음 : {}", key);
            return;
        }

        Optional.ofNullable(jobStore.get(key))
                .ifPresent(future -> {
                    future.cancel(false);
                    remove(key);
                });
    }

    private void remove(String key){
        jobStore.remove(key);
    }

    private void store(String key, Future<?> job){
        jobStore.put(key, job);
    }

    private String createKey(Recruitment target, RecruitmentStates states){
        return String.format("ID_%s_TO_%s", target.getId(), states.name());
    }
}
