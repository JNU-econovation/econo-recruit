package com.econovation.recruitdomain.out;

import com.econovation.recruitdomain.domains.period.domain.Period;

public interface PeriodRecordPort {

    void save(Period period);
}
