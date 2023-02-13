package com.econovation.recruit.adapter.out.persistence;

import com.econovation.recruit.application.port.out.LoadApplicantPort;
import com.econovation.recruit.application.port.out.RecordApplicantPort;
import com.econovation.recruit.domain.applicant.Applicant;
import com.econovation.recruit.domain.applicant.ApplicantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApplicantPersistenceAdapter implements RecordApplicantPort, LoadApplicantPort {
    private final ApplicantRepository applicantRepository;
    @Override
    public Applicant save(Applicant applicant) {
        return applicantRepository.save(applicant);
    }

    @Override
    public Boolean isDuplicate(Applicant applicant) {
        return applicantRepository.existsById(applicant.getId());
    }
}
