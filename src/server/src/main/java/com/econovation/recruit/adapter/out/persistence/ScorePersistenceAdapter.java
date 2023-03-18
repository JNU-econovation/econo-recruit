package com.econovation.recruit.adapter.out.persistence;

import com.econovation.recruit.application.port.out.ScoreLoadPort;
import com.econovation.recruit.application.port.out.ScoreRecordPort;
import com.econovation.recruit.domain.applicant.Applicant;
import com.econovation.recruit.domain.score.Score;
import com.econovation.recruit.domain.score.ScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ScorePersistenceAdapter implements ScoreLoadPort, ScoreRecordPort {
    private final ScoreRepository scoreRepository;
    @Override
    public Score save(Score score) {
        return scoreRepository.save(score);
    }
    @Override
    public List<Score> findByApplicant(Applicant applicant) {
        return scoreRepository.findByApplicant(applicant);
    }
}