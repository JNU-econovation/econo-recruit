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

    private static final String NO_MATCH_APPLICANT_MESSSAGE = "해당하는 지원자가 없습니다.";
    private final ApplicantRepository applicantRepository;
    @Override
    public Applicant save(Applicant applicant) {
        return applicantRepository.save(applicant);
    }

    @Override
    public Boolean isDuplicate(Applicant applicant) {
        return applicantRepository.existsById(applicant.getId());
    }

    @Override
    public Applicant loadApplicantById(Integer applicantId) {
        return applicantRepository.findById(Long.valueOf(applicantId))
                .orElseThrow(() -> new IllegalArgumentException(NO_MATCH_APPLICANT_MESSSAGE));
    }

}
