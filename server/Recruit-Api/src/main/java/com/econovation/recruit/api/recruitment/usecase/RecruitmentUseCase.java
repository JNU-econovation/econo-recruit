package com.econovation.recruit.api.recruitment.usecase;

import com.econovation.recruitdomain.domains.recruitment.domain.Recruitment;
import java.time.LocalDateTime;
import java.util.List;

public interface RecruitmentUseCase {

    Long setUp(Integer year, LocalDateTime startAt, LocalDateTime endAt);

    Recruitment getLatestOne();

    void terminate(Long recruitmentId);

    List<Recruitment> findAllOrderByNewest();

    List<Recruitment> getPage(int page);
}
