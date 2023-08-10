package com.econovation.recruitdomain.out;

import com.econovation.recruitdomain.domains.interviewer.Interviewer;
import java.util.List;

public interface InterviewerRecordPort {
    List<Interviewer> saveAll(List<Interviewer> a);
}
