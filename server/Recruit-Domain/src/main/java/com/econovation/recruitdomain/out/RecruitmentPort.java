package com.econovation.recruitdomain.out;

import com.econovation.recruitdomain.domains.applicant.domain.state.RecruitmentStates;
import com.econovation.recruitdomain.domains.recruitment.domain.Recruitment;
import java.util.List;
import java.util.Optional;

public interface RecruitmentPort {

    List<Recruitment> findByStates(RecruitmentStates states);

    List<Recruitment> findAllOrderByNewest();

    Optional<Recruitment> findById(Long id);

    Optional<Recruitment> findLatestOne();

    Recruitment save(Recruitment entity);

    boolean existsNonStart();

    void delete(Long id);
}
