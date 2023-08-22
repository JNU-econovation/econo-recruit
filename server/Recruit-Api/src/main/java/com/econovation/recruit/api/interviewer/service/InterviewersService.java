package com.econovation.recruit.api.interviewer.service;

import com.econovation.recruit.api.interviewer.usecase.InterviewerUseCase;
import com.econovation.recruitdomain.domains.dto.InterviewerCreateDto;
import com.econovation.recruitdomain.domains.interviewer.domain.Interviewer;
import com.econovation.recruitdomain.out.InterviewerLoadPort;
import com.econovation.recruitdomain.out.InterviewerRecordPort;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InterviewersService implements InterviewerUseCase {
    private InterviewerLoadPort interviewerLoadPort;
    private InterviewerRecordPort interviewerRecordPort;

    @Override
    public List<Interviewer> createInterviewers(
            @NotNull List<InterviewerCreateDto> interviewerCreateDto) {
        List<Interviewer> interviewers =
                interviewerCreateDto.stream()
                        .map(InterviewerCreateDto::from)
                        .collect(Collectors.toList());
        return interviewerRecordPort.saveAll(interviewers);
    }

    @Override
    public Interviewer getById(Long idpId) {
        return interviewerLoadPort.loadInterviewById(idpId);
    }

    @Override
    public void updateRole(Long idpId, String role) {}
}
