package com.econovation.recruitdomain.persistence;


import com.econovation.recruitdomain.domain.applicant.Applicant;
import com.econovation.recruitdomain.domain.applicant.ApplicantRepository;
import com.econovation.recruitdomain.out.ApplicantLoadPort;
import com.econovation.recruitdomain.out.ApplicantRecordPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApplicantPersistenceAdapter implements ApplicantRecordPort, ApplicantLoadPort {
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
        return applicantRepository
                .findById(applicantId)
                .orElseThrow(() -> new IllegalArgumentException(NO_MATCH_APPLICANT_MESSSAGE));
    }
}
