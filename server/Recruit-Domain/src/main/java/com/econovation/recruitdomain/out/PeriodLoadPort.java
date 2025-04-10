package com.econovation.recruitdomain.out;

import com.econovation.recruitdomain.domains.period.domain.Period;
import java.util.Optional;

public interface PeriodLoadPort {

    Optional<Period> findLatestOne();

}
