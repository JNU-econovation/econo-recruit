package com.econovation.recruitdomain.out;

import com.econovation.recruitdomain.domains.interviewer.domain.Interviewer;
import java.util.List;
import org.springframework.stereotype.Component;

public interface InterviewerLoadPort {
    Interviewer loadInterviewById(Integer idpId);
}