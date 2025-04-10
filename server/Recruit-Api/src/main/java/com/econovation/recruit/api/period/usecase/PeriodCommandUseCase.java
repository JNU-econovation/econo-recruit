package com.econovation.recruit.api.period.usecase;

import com.econovation.recruitdomain.domains.dto.CreatePeriodDto;
import java.time.LocalDateTime;

public interface PeriodCommandUseCase {

    void register(CreatePeriodDto request);

}
