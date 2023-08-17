package com.econovation.recruitdomain.out;

import com.econovation.recruitdomain.domains.interviewer.domain.Interviewer;
import java.util.List;

public interface InterviewerLoadPort {
    Interviewer loadInterviewById(Integer idpId);

    List<Interviewer> loadInterviewerByIdpIds(List<Integer> idpIds);
}
