package com.econovation.recruit.adapter.out.persistence;


import com.econovation.recruit.application.port.out.InterviewerRecordPort;
import com.econovation.recruit.application.port.out.LoadInterviewerPort;
import com.econovation.recruit.domain.interviewer.Interviewer;
import com.econovation.recruit.domain.interviewer.InterviewerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class InterviewerPersistenceAdapter implements LoadInterviewerPort, InterviewerRecordPort {
    private static final String NO_MATCH_INTERVIEWER_MESSAGE = "해당하는 면접관이 없습니다.";
    private final InterviewerRepository interviewerRepository;

    @Override
    public Interviewer loadInterviewById(Integer idpId) {
        return interviewerRepository.findById(Long.valueOf(idpId))
                .orElseThrow(() -> new IllegalArgumentException(NO_MATCH_INTERVIEWER_MESSAGE));
    }
    @Override
    public List<Interviewer> saveAll(List<Interviewer> interviewer) {
        return interviewerRepository.saveAll(interviewer);
    }
}
