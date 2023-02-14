package com.econovation.recruit.application.port.out;

import com.econovation.recruit.domain.applicant.Applicant;

public interface LoadApplicantPort {
    Boolean isDuplicate(Applicant applicant);

    Applicant loadApplicantById(Integer applicantId);
}
