package com.econovation.recruitdomain.domains.recruitment.adaptor;

import com.econovation.recruitcommon.annotation.Adaptor;
import com.econovation.recruitdomain.domains.recruitment.domain.RecruitmentStates;
import com.econovation.recruitdomain.domains.recruitment.domain.Recruitment;
import com.econovation.recruitdomain.domains.recruitment.domain.RecruitmentRepository;
import com.econovation.recruitdomain.out.RecruitmentPort;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

@Adaptor
@RequiredArgsConstructor
public class RecruitmentAdaptor implements RecruitmentPort {

    private final RecruitmentRepository repository;

    @Override
    public Optional<Recruitment> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Recruitment> findLatestOne() {
        List<Recruitment> recruitments = repository.findAllOrderByUpdatedAt();

        if (!recruitments.isEmpty()) {
            return Optional.ofNullable(recruitments.get(0));
        }

        return Optional.empty();
    }

    @Override
    public List<Recruitment> findAllOrderByNewest() {
        Sort sort = Sort.by(Order.desc("createdAt"));
        return repository.findAll(sort);
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

    @Override
    public List<Recruitment> findByStates(RecruitmentStates states) {
        return repository.findByStates(states);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
