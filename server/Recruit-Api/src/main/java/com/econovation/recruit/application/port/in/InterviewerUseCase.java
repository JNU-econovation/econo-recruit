package com.econovation.recruit.application.port.in;


import com.econovation.recruitdomain.domain.dto.InterviewerCreateDto;
import com.econovation.recruitdomain.domain.interviewer.Interviewer;
import java.util.List;

public interface InterviewerUseCase {
    List<Interviewer> createInterviewers(List<InterviewerCreateDto> interviewerCreateDto);

    Interviewer getById(Integer idpId);
}
