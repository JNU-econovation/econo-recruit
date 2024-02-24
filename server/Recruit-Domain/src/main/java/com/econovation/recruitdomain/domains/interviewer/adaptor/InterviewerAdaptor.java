package com.econovation.recruitdomain.domains.interviewer.adaptor;

import com.econovation.recruitcommon.annotation.Adaptor;
import com.econovation.recruitdomain.domains.interviewer.domain.Interviewer;
import com.econovation.recruitdomain.domains.interviewer.domain.InterviewerRepository;
import com.econovation.recruitdomain.domains.interviewer.domain.Role;
import com.econovation.recruitdomain.domains.interviewer.exception.InterviewerNotFoundException;
import com.econovation.recruitdomain.out.InterviewerLoadPort;
import com.econovation.recruitdomain.out.InterviewerRecordPort;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@Adaptor
@RequiredArgsConstructor
public class InterviewerAdaptor implements InterviewerRecordPort, InterviewerLoadPort {
    private final InterviewerRepository interviewerRepository;

    @Override
    public Interviewer loadInterviewById(Long idpId) {
        return interviewerRepository
                .findById(idpId)
                .orElseThrow(() -> InterviewerNotFoundException.EXCEPTION);
    }

    @Override
    public List<Interviewer> loadInterviewerByIdpIds(List<Long> idpIds) {
        return interviewerRepository.findAllById(idpIds);
    }

    @Override
    public List<Interviewer> findAll() {
        return interviewerRepository.findAll();
    }

    @Override
    public Interviewer loadInterviewerByEmail(String email) {
        return interviewerRepository
                .findByEmail(email)
                .orElseThrow(() -> InterviewerNotFoundException.EXCEPTION);
    }

    @Override
    public Optional<Interviewer> loadOptionalInterviewerByEmail(String email) {
        return interviewerRepository.findByEmail(email);
    }

    @Override
    public List<Interviewer> loadInterviewerByRole(Role role) {
        return interviewerRepository.findByRole(role);
    }

    public List<Interviewer> saveAll(List<Interviewer> interviewer) {
        return interviewerRepository.saveAll(interviewer);
    }

    @Override
    public void save(Interviewer interviewer) {
        interviewerRepository.save(interviewer);
    }

    @Override
    public void deleteById(Long idpId) {
        interviewerRepository.deleteById(idpId);
    }
}
