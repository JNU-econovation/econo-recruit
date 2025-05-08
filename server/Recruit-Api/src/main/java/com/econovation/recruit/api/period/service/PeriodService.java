package com.econovation.recruit.api.period.service;

import com.econovation.recruit.api.period.usecase.PeriodCommandUseCase;
import com.econovation.recruit.api.period.usecase.PeriodQueryUseCase;
import com.econovation.recruitdomain.domains.dto.CreatePeriodDto;
import com.econovation.recruitdomain.domains.dto.PeriodResponseDto;
import com.econovation.recruitdomain.domains.period.domain.Period;
import com.econovation.recruitdomain.out.PeriodRecordPort;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PeriodService implements PeriodQueryUseCase, PeriodCommandUseCase {

    private final PeriodProvider periodProvider;
    private final PeriodRecordPort periodRecordPort;

    @Override
    public void register(CreatePeriodDto request) {
        Period period =
                Period.of(
                        request.getRecruitStart(),
                        request.getRecruitEnd(),
                        request.getPassDate(),
                        request.getFirstDiscussionEnd(),
                        request.getFinalDiscussionEnd());

        periodRecordPort.save(period);
    }

    @Override
    public PeriodResponseDto get() {
        LocalDateTime now = LocalDateTime.now();
        Period period = periodProvider.get();

        return PeriodResponseDto.of(
                period.getRecruitStart(),
                period.getRecruitEnd(),
                period.getPassDate(),
                period.getFirstDiscussionEnd(),
                period.getFinalDiscussionEnd());
    }
}
