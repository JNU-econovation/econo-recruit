package com.econovation.recruitdomain.out;

import com.econovation.recruitcommon.annotation.Port;
import com.econovation.recruitdomain.domains.interviewer.Interviewer;
import java.util.List;

@Port
public interface InterviewerRecordPort {
    List<Interviewer> saveAll(List<Interviewer> a);
}