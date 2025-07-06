package com.econovation.recruitdomain.domains.recruitment.adaptor;

import com.econovation.recruitcommon.annotation.Adaptor;
import com.econovation.recruitdomain.domains.applicant.domain.state.RecruitmentStates;
import com.econovation.recruitdomain.domains.recruitment.domain.Recruitment;
import com.econovation.recruitdomain.domains.recruitment.domain.RecruitmentRepository;
import com.econovation.recruitdomain.out.RecruitmentPort;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@Adaptor
@RequiredArgsConstructor
public class RecruitmentAdaptor implements RecruitmentPort {

    private final RecruitmentRepository repository;

    @Override
    public Optional<Recruitment> findLatestOne() {
        return repository.findLatestOne();
    }

    @Override
    public Recruitment save(Recruitment entity) {
        return repository.save(entity);
    }

    @Override
    public boolean existsNonStart() {
        List<Recruitment> recruitments = repository.findByStates(RecruitmentStates.NON_START);

        return !recruitments.isEmpty();
    }
}
