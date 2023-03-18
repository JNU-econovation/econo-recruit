package com.econovation.recruit.application.port.out;

import com.econovation.recruit.domain.interviewer.Interviewer;

public interface LoadInterviewerPort {
    Interviewer loadInterviewById(Integer idpId);
}