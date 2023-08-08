package com.econovation.recruitdomain.out;


import com.econovation.recruitdomain.domains.interviewer.Interviewer;

public interface LoadInterviewerPort {
    Interviewer loadInterviewById(Integer idpId);
}
