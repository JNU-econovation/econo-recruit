package com.econovation.recruitdomain.out;

import com.econovation.recruitdomain.domains.recruitment.domain.Recruitment;
import java.util.Optional;

public interface RecruitmentPort {

    Optional<Recruitment> findLatestOne();

    Recruitment save(Recruitment entity);

}
