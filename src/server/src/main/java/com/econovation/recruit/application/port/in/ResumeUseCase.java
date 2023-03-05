package com.econovation.recruit.application.port.in;

import com.econovation.recruit.domain.dto.ResumeInsertDto;
import com.econovation.recruit.domain.resume.Resume;

import java.util.HashMap;
import java.util.List;

public interface ResumeUseCase {
//    List<ResumeInsertDto> submitResume(HashMap<String, Object> param);
    List<Resume> submitResume(List<Resume> resumes);

    List<Resume> findAll();

    Resume findById(Integer resumeId);
    List<Resume> findByApplicantId(Integer applicantId);
}
