package com.econovation.recruitdomain.out;

import com.econovation.recruitdomain.domains.interviewer.domain.Interviewer;
import java.util.List;

public interface InterviewerLoadPort {
    Interviewer loadInterviewById(Long idpId);

    List<Interviewer> loadInterviewerByIdpIds(List<Long> idpIds);
}
