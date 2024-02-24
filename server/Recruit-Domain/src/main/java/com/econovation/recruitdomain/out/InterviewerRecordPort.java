package com.econovation.recruitdomain.out;

import com.econovation.recruitdomain.domains.interviewer.domain.Interviewer;
import java.util.List;

public interface InterviewerRecordPort {
    List<Interviewer> saveAll(List<Interviewer> a);

    void save(Interviewer interviewer);

    void deleteById(Long idpId);
}
