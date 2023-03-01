package com.econovation.recruit.adapter.out.persistence;

import com.econovation.recruit.application.port.out.ApplicantLoadPort;
import com.econovation.recruit.application.port.out.ApplicantRecordPort;
import com.econovation.recruit.domain.applicant.Applicant;
import com.econovation.recruit.domain.applicant.ApplicantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApplicantPersistenceAdapterRecordLoad implements ApplicantRecordPort, ApplicantLoadPort {
    private static final String NO_MATCH_APPLICANT_MESSSAGE = "해당하는 지원자가 없습니다.";
    private final ApplicantRepository applicantRepository;
    @Override
    public Applicant save(Applicant applicant) {
        return applicantRepository.save(applicant);
    }

    @Override
    public Boolean isDuplicate(Applicant applicant) {
        return applicantRepository.existsByEmail(applicant.getEmail());
    }

    @Override
    public Applicant loadApplicantById(Integer applicantId) {
        return applicantRepository.findById(applicantId)
                .orElseThrow(() -> new IllegalArgumentException(NO_MATCH_APPLICANT_MESSSAGE));
    }
}