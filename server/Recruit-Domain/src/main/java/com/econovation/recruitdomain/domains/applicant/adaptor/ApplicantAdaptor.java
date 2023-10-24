package com.econovation.recruitdomain.domains.applicant.adaptor;

import com.econovation.recruitcommon.annotation.Adaptor;
import com.econovation.recruitdomain.domain.applicant.Applicant;
import com.econovation.recruitdomain.domains.applicant.domain.ApplicantRepository;
import lombok.RequiredArgsConstructor;

@Adaptor
@RequiredArgsConstructor
public class ApplicantAdaptor {
    private final ApplicantRepository applicantRepository;

    public void save(Applicant applicant) {
        applicantRepository.save(applicant);
    }
}
