package com.econovation.recruit.application.utils;

import com.econovation.recruit.domain.applicant.Applicant;
import com.econovation.recruit.domain.dto.ApplicantRegisterDto;

public interface ApplicantMapper {
    Applicant toEntity(ApplicantRegisterDto applicantRegisterDto);
}
