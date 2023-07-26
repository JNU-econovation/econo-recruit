package com.econovation.recruitdomain.out;


import com.econovation.recruitdomain.domain.interviewer.Interviewer;
import java.util.List;

public interface InterviewerRecordPort {
    List<Interviewer> saveAll(List<Interviewer> a);
}
