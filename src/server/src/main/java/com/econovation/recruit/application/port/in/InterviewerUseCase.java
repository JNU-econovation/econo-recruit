package com.econovation.recruit.application.port.in;

import com.econovation.recruit.domain.dto.InterviewerCreateDto;
import com.econovation.recruit.domain.interviewer.Interviewer;

import java.util.List;

public interface InterviewerUseCase {
    List<Interviewer> createInterviewers(List<InterviewerCreateDto> interviewerCreateDto);

    Interviewer getById(Integer idpId);
}
