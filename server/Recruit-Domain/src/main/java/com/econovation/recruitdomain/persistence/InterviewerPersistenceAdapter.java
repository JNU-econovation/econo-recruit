package com.econovation.recruitdomain.persistence;


import com.econovation.recruitdomain.domain.interviewer.Interviewer;
import com.econovation.recruitdomain.domain.interviewer.InterviewerRepository;
import com.econovation.recruitdomain.out.InterviewerRecordPort;
import com.econovation.recruitdomain.out.LoadInterviewerPort;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class InterviewerPersistenceAdapter implements LoadInterviewerPort, InterviewerRecordPort {
    private static final String NO_MATCH_INTERVIEWER_MESSAGE = "해당하는 면접관이 없습니다.";
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
