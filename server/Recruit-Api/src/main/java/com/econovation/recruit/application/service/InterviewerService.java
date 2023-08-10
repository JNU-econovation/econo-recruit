package com.econovation.recruit.application.service;

import com.econovation.recruit.application.port.in.InterviewerUseCase;
import com.econovation.recruit.application.utils.EntityMapper;
import com.econovation.recruitdomain.domains.dto.InterviewerCreateDto;
import com.econovation.recruitdomain.domains.interviewer.Interviewer;
import com.econovation.recruitdomain.out.InterviewerRecordPort;
import com.econovation.recruitdomain.out.LoadInterviewerPort;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InterviewerService implements InterviewerUseCase {
    private final EntityMapper entityMapper;
    private final InterviewerRecordPort interviewerRecordPort;
    private final LoadInterviewerPort loadInterviewerPort;

    @Override
    public List<Interviewer> createInterviewers(List<InterviewerCreateDto> interviewerCreateDto) {
        List<Interviewer> interviewers = entityMapper.toInterviewers(interviewerCreateDto);
        return interviewerRecordPort.saveAll(interviewers);
    }

    @Override
    public Interviewer getById(Integer idpId) {
        return loadInterviewerPort.loadInterviewById(idpId);
    }
}
