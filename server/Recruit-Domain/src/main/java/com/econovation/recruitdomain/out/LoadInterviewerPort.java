package com.econovation.recruitdomain.out;


import com.econovation.recruitdomain.domain.interviewer.Interviewer;

public interface LoadInterviewerPort {
    Interviewer loadInterviewById(Integer idpId);
}
