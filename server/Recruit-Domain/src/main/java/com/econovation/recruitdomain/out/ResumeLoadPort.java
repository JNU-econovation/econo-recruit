package com.econovation.recruitdomain.out;

import com.econovation.recruitdomain.domains.resume.Resume;
import java.util.List;

public interface ResumeLoadPort {
    List<Resume> findAll();

    Resume findById(Integer resumeId);

    List<Resume> findByApplicantId(Integer applicantId);
}
