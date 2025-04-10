package com.econovation.recruit.api.period.service;

import com.econovation.recruit.api.period.usecase.PeriodCommandUseCase;
import com.econovation.recruit.api.period.usecase.PeriodQueryUseCase;
import com.econovation.recruitdomain.domains.dto.CreatePeriodDto;
import com.econovation.recruitdomain.domains.dto.PeriodResponseDto;
import com.econovation.recruitdomain.out.PeriodLoadPort;
import com.econovation.recruitdomain.out.PeriodRecordPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PeriodService implements PeriodQueryUseCase, PeriodCommandUseCase {

    private final PeriodLoadPort periodLoadPort;
    private final PeriodRecordPort periodRecordPort;

    @Override
    public void register(CreatePeriodDto request) {
    }

    @Override
    public PeriodResponseDto get() {
        return null;
    }
}
