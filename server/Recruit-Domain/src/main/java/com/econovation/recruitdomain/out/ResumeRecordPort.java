package com.econovation.recruitdomain.out;


import com.econovation.recruitdomain.domain.resume.Resume;
import java.util.List;

public interface ResumeRecordPort {
    //    List<ResumeInsertDto> saveAll(List<ResumeInsertDto> resumes);
    List<Resume> saveAll(List<Resume> resumes);
}
