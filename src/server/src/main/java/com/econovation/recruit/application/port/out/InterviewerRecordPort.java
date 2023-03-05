package com.econovation.recruit.application.port.out;

import com.econovation.recruit.domain.interviewer.Interviewer;

import java.util.List;

public interface InterviewerRecordPort {
    List<Interviewer> saveAll(List<Interviewer> a);
}
