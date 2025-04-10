package com.econovation.recruitdomain.domains.period.adaptor;

import com.econovation.recruitcommon.annotation.Adaptor;
import com.econovation.recruitdomain.domains.period.domain.Period;
import com.econovation.recruitdomain.domains.period.domain.PeriodRepository;
import com.econovation.recruitdomain.out.PeriodLoadPort;
import com.econovation.recruitdomain.out.PeriodRecordPort;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@Adaptor
@RequiredArgsConstructor
public class PeriodAdaptor implements PeriodRecordPort, PeriodLoadPort {

    private final PeriodRepository repository;

    @Override
    public Optional<Period> findLatestOne() {
        return repository.findLatestOne();
    }

    @Override
    public void save(Period period) {
        repository.save(period);
    }
}
