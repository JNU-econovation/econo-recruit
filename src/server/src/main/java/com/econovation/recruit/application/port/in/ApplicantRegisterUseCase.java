package com.econovation.recruit.application.port.in;

import com.econovation.recruit.domain.applicant.Applicant;

public interface ApplicantRegisterUseCase {
    void apply(Applicant toEntity);
}
