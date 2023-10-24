package com.econovation.recruitdomain.out;

import com.econovation.recruitdomain.domains.interviewer.domain.Interviewer;

public interface LoadInterviewerPort {
    Interviewer loadInterviewById(Integer idpId);
}
