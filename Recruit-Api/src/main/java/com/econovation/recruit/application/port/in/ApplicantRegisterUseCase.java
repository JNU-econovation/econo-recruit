package com.econovation.recruit.application.port.in;


import com.econovation.recruitdomain.domain.applicant.Applicant;

public interface ApplicantRegisterUseCase {
    void apply(Applicant toEntity);
}
