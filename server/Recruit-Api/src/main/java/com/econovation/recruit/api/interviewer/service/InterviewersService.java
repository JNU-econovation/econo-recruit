package com.econovation.recruit.api.interviewer.service;

import com.econovation.recruit.api.config.security.SecurityUtils;
import com.econovation.recruit.api.interviewer.helper.IdpHelper;
import com.econovation.recruit.api.interviewer.usecase.InterviewerUseCase;
import com.econovation.recruit.utils.sort.SortHelper;
import com.econovation.recruitdomain.domains.dto.InterviewerCreateDto;
import com.econovation.recruitdomain.domains.dto.InterviewerResponseDto;
import com.econovation.recruitdomain.domains.interviewer.domain.Interviewer;
import com.econovation.recruitdomain.domains.interviewer.domain.Role;
import com.econovation.recruitdomain.out.InterviewerLoadPort;
import com.econovation.recruitdomain.out.InterviewerRecordPort;
import com.econovation.recruitinfrastructure.idp.dto.InterviewerResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InterviewersService implements InterviewerUseCase {
    private final InterviewerLoadPort interviewerLoadPort;
    private final InterviewerRecordPort interviewerRecordPort;
    private final IdpHelper idpHelper;
    private final SortHelper<Interviewer> interviewerSortHelper;

    @Override
    public List<Interviewer> createInterviewers(List<Long> idpIds) {
        List<InterviewerResponse> interviewers = idpHelper.createInterviewers(idpIds);
        return interviewerRecordPort.saveAll(
                interviewers.stream().map(InterviewerCreateDto::from).toList());
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
        // 해당 유저를 logOut 시킨다

    }

    @Override
    @Transactional
    public void createInterviewersByName(List<String> names) {
        List<InterviewerResponse> interviewers = idpHelper.loadByNames(names);
        interviewerRecordPort.saveAll(
                interviewers.stream().map(InterviewerCreateDto::from).toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<InterviewerResponseDto> findAll() {
        List<Interviewer> interviewers = interviewerLoadPort.findAll();
        return interviewers.stream().map(InterviewerResponseDto::from).toList();
    }

    @Override
    public List<InterviewerResponseDto> findAll(String sortType) {
        List<Interviewer> interviewers = interviewerLoadPort.findAll();
        interviewerSortHelper.sort(interviewers, sortType);
        return interviewers.stream().map(InterviewerResponseDto::from).toList();
    }

    @Override
    @Transactional
    public void createTempInterviewers() {
        interviewerRecordPort.save(
                Interviewer.builder()
                        .id(0L)
                        .name("이서현 (임시 면접관)")
                        .role(Role.ROLE_OPERATION)
                        .year(21)
                        .build());
    }

    @Override
    @Transactional(readOnly = true)
    public InterviewerResponseDto findMe() {
        Long userId = SecurityUtils.getCurrentUserId();
        Interviewer interviewer = interviewerLoadPort.loadInterviewById(userId);
        return InterviewerResponseDto.from(interviewer);
    }
}
