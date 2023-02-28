package com.econovation.recruit.application.port.out;

import com.econovation.recruit.domain.dto.ResumeInsertDto;

import javax.transaction.Transactional;
import java.util.List;

public interface ResumeRecordPort {

    @Transactional
    List<ResumeInsertDto> saveAll(List<ResumeInsertDto> resumes);
}
