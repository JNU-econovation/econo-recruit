package com.econovation.recruit.api.recruitment.usecase;

import com.econovation.recruitdomain.domains.recruitment.domain.Recruitment;
import java.time.LocalDateTime;

public interface RecruitmentUseCase {

    Long setUp(Long year, LocalDateTime startAt, LocalDateTime endAt);

    Recruitment getLatestOne();

    void terminate(Long recruitmentId);
}
