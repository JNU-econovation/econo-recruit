package com.econovation.recruitdomain.out;


import com.econovation.recruitdomain.domain.applicant.Applicant;

public interface ApplicantLoadPort {
    Boolean isDuplicate(Applicant applicant);

    Applicant loadApplicantById(Integer applicantId);
}
