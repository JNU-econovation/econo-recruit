package com.econovation.recruit.api.recruitment.service;

import com.econovation.recruit.api.recruitment.quartz.RecruitmentScheduler;
import com.econovation.recruit.api.recruitment.usecase.RecruitmentUseCase;
import com.econovation.recruitdomain.common.aop.domainEvent.Events;
import com.econovation.recruitdomain.domains.recruitment.domain.RecruitmentStates;
import com.econovation.recruitdomain.domains.recruitment.domain.Recruitment;
import com.econovation.recruitdomain.domains.recruitment.event.RecruitmentRegistered;
import com.econovation.recruitdomain.domains.recruitment.event.RecruitmentTerminated;
import com.econovation.recruitdomain.domains.recruitment.exception.RecruitmentAlreadyExistsException;
import com.econovation.recruitdomain.domains.recruitment.exception.RecruitmentNotFoundException;
import com.econovation.recruitdomain.out.RecruitmentPort;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RecruitmentService implements RecruitmentUseCase {

    private final RecruitmentPort recruitmentPort;
    private final RecruitmentScheduler recruitmentScheduler;

    @Override
    @Transactional
    public Long setUp(Integer year, LocalDateTime startAt, LocalDateTime endAt) {
        validateCreateRecruit();

        Recruitment recruitment =
                Recruitment.builder()
                        .endAt(endAt)
                        .startAt(startAt)
                        .year(year)
                        .states(RecruitmentStates.NON_START)
                        .build();

        Recruitment saved = recruitmentPort.save(recruitment);

        // 이벤트 발행
        Events.raise(new RecruitmentRegistered(saved.getId()));

        return saved.getId();
    }

    @Override
    public Recruitment getLatestOne() {
        return recruitmentPort
                .findLatestOne()
                .orElseThrow(() -> RecruitmentNotFoundException.EXCEPTION);
    }

    @Override
    public List<Recruitment> getPage(int page, int pageSize) {
        List<Recruitment> recruitments = recruitmentPort.findAllOrderByNewest();

        int start = (page - 1) * pageSize;
        int end = Math.min(page * pageSize, recruitments.size());

        return recruitments.subList(start, end);
    }

    @Override
    public List<Recruitment> findAllOrderByNewest() {
        return recruitmentPort.findAllOrderByNewest();
    }

    @Override
    @Transactional
    public void terminate(Long recruitmentId) {
        recruitmentPort
                .findById(recruitmentId)
                .ifPresent(
                        recruitment -> {
                            if (recruitment.getStates().equals(RecruitmentStates.NON_START))
                                recruitmentPort.delete(recruitmentId);
                            else {
                                recruitment.updateStates(RecruitmentStates.END);
                                recruitmentPort.save(recruitment);
                            }
                        });

        Events.raise(new RecruitmentTerminated(recruitmentId));
    }

    public void validateCreateRecruit() {
        recruitmentPort
                .findLatestOne()
                .map(Recruitment::getStates)
                .ifPresent(
                        states -> {
                            if (states.equals(RecruitmentStates.NON_START)
                                    || states.equals(RecruitmentStates.RECRUITING))
                                throw new RecruitmentAlreadyExistsException();
                        });
    }
}
