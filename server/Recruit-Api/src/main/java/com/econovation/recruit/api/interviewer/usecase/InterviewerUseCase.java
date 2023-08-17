package com.econovation.recruit.api.interviewer.usecase;

import com.econovation.recruitcommon.annotation.UseCase;
import com.econovation.recruitdomain.domains.dto.InterviewerCreateDto;
import com.econovation.recruitdomain.domains.interviewer.domain.Interviewer;
import java.util.List;

@UseCase
public interface InterviewerUseCase {
    List<Interviewer> createInterviewers(List<InterviewerCreateDto> interviewerCreateDto);

    Interviewer getById(Integer idpId);
}
