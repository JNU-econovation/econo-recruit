package com.econovation.recruit.api.period.usecase;

import com.econovation.recruitdomain.domains.dto.CreatePeriodDto;

public interface PeriodCommandUseCase {

    void register(CreatePeriodDto request);
}
