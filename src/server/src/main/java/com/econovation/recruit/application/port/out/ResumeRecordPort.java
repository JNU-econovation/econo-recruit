package com.econovation.recruit.application.port.out;

import com.econovation.recruit.domain.dto.ResumeInsertDto;
import com.econovation.recruit.domain.resume.Resume;

import javax.transaction.Transactional;
import java.util.List;

public interface ResumeRecordPort {
//    List<ResumeInsertDto> saveAll(List<ResumeInsertDto> resumes);
    List<Resume> saveAll(List<Resume> resumes);
}
