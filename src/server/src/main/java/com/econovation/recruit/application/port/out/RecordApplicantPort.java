package com.econovation.recruit.application.port.out;

import com.econovation.recruit.domain.applicant.Applicant;

public interface RecordApplicantPort {
    Applicant save(Applicant applicant);
}
