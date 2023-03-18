package com.econovation.recruit.application.port.out;

import com.econovation.recruit.domain.applicant.Applicant;
import com.econovation.recruit.domain.resume.Resume;

import java.util.List;

public interface ResumeLoadPort {
    List<Resume> findAll();

    Resume findById(Integer resumeId);

    List<Resume> findByApplicant(Applicant applicant);
}
