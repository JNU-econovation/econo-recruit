package com.econovation.recruit.api.interviewer.service;

import com.econovation.recruit.api.interviewer.helper.IdpHelper;
import com.econovation.recruit.api.interviewer.usecase.InterviewerUseCase;
import com.econovation.recruitdomain.domains.dto.InterviewerCreateDto;
import com.econovation.recruitdomain.domains.interviewer.domain.Interviewer;
import com.econovation.recruitdomain.domains.interviewer.domain.Role;
import com.econovation.recruitdomain.out.InterviewerLoadPort;
import com.econovation.recruitdomain.out.InterviewerRecordPort;
import com.econovation.recruitinfrastructure.idp.dto.InterviewerResponse;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InterviewersService implements InterviewerUseCase {
    private final InterviewerLoadPort interviewerLoadPort;
    private final InterviewerRecordPort interviewerRecordPort;
    private final IdpHelper idpHelper;

    @Override
    public List<Interviewer> createInterviewers(List<Long> idpIds) {
        List<InterviewerResponse> interviewers = idpHelper.createInterviewers(idpIds);
        return interviewerRecordPort.saveAll(
                interviewers.stream().map(InterviewerCreateDto::from).collect(Collectors.toList()));
    }

    @Override
    public Interviewer getById(Long idpId) {
        return interviewerLoadPort.loadInterviewById(idpId);
    }

    @Override
    @Transactional
    public void updateRole(Long idpId, String role) {
        Interviewer interviewer = interviewerLoadPort.loadInterviewById(idpId);
        interviewer.updateRole(Role.getByName(role));
    }

    @Override
    public void createInterviewersByName(List<String> names) {
        List<InterviewerResponse> interviewers = idpHelper.loadByNames(names);
        interviewerRecordPort.saveAll(
                interviewers.stream().map(InterviewerCreateDto::from).collect(Collectors.toList()));
    }

    @Override
    public List<Interviewer> findAll() {
        return interviewerLoadPort.findAll();
    }

    @Override
    public void createTempInterviewers() {
        interviewerRecordPort.save(
                Interviewer.builder()
                        .id(0L)
                        .name("이서현 (임시 면접관)")
                        .role(Role.ROLE_OPERATION)
                        .year(21)
                        .build());
    }
}
