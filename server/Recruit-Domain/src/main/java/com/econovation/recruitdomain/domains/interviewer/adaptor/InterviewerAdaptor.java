package com.econovation.recruitdomain.domains.interviewer.adaptor;

import static com.econovation.recruitcommon.consts.RecruitStatic.NO_MATCH_INTERVIEWER_MESSAGE;

import com.econovation.recruitcommon.annotation.Adaptor;
import com.econovation.recruitdomain.domains.interviewer.domain.Interviewer;
import com.econovation.recruitdomain.domains.interviewer.domain.InterviewerRepository;
import com.econovation.recruitdomain.out.InterviewerLoadPort;
import com.econovation.recruitdomain.out.InterviewerRecordPort;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Adaptor
@RequiredArgsConstructor
public class InterviewerAdaptor implements InterviewerRecordPort, InterviewerLoadPort {
    private final InterviewerRepository interviewerRepository;

    public Interviewer loadInterviewById(Integer idpId) {
        return interviewerRepository
                .findById(idpId)
                .orElseThrow(() -> new IllegalArgumentException(NO_MATCH_INTERVIEWER_MESSAGE));
    }

    @Override
    public List<Interviewer> loadInterviewerByIdpIds(List<Integer> idpIds) {
        return interviewerRepository.findAllById(idpIds);
    }

    public List<Interviewer> saveAll(List<Interviewer> interviewer) {
        return interviewerRepository.saveAll(interviewer);
    }
}
