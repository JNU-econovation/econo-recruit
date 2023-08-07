package com.econovation.recruitdomain.out;


import com.econovation.recruitdomain.domain.applicant.Applicant;

public interface ApplicantRecordPort {
    Applicant save(Applicant applicant);
}
