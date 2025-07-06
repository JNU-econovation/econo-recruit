package com.econovation.recruitdomain.out;

import com.econovation.recruitdomain.domains.recruitment.domain.Recruitment;
import java.util.Optional;

public interface RecruitmentPort {

    Optional<Recruitment> findById(Long id);

    Optional<Recruitment> findLatestOne();

    Recruitment save(Recruitment entity);

    boolean existsNonStart();

}
