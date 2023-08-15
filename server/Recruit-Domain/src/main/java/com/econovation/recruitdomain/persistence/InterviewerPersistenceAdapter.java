package com.econovation.recruitdomain.persistence;

import static com.econovation.recruitcommon.consts.RecruitStatic.NO_MATCH_INTERVIEWER_MESSAGE;

import com.econovation.recruitcommon.annotation.Adaptor;
import com.econovation.recruitdomain.domains.interviewer.Interviewer;
import com.econovation.recruitdomain.domains.interviewer.InterviewerRepository;
import com.econovation.recruitdomain.out.InterviewerRecordPort;
import com.econovation.recruitdomain.out.LoadInterviewerPort;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Adaptor
public class InterviewerPersistenceAdapter implements LoadInterviewerPort, InterviewerRecordPort {
    private final InterviewerRepository interviewerRepository;

    @Override
    public Interviewer loadInterviewById(Integer idpId) {
        return interviewerRepository
                .findById(Integer.valueOf(idpId))
                .orElseThrow(() -> new IllegalArgumentException(NO_MATCH_INTERVIEWER_MESSAGE));
    }

    @Override
    public List<Interviewer> saveAll(List<Interviewer> interviewer) {
        return interviewerRepository.saveAll(interviewer);
    }
}
