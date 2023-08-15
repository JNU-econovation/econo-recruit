package com.econovation.recruitdomain.persistence;

import com.econovation.recruitdomain.domains.score.Score;
import com.econovation.recruitdomain.domains.score.ScoreRepository;
import com.econovation.recruitdomain.out.ScoreLoadPort;
import com.econovation.recruitdomain.out.ScoreRecordPort;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScorePersistenceAdapter implements ScoreLoadPort, ScoreRecordPort {
    private final ScoreRepository scoreRepository;

    @Override
    public Score save(Score score) {
        return scoreRepository.save(score);
    }

    @Override
    public List<Score> findByApplicantId(Integer applicantId) {
        return scoreRepository.findByApplicantId(applicantId);
    }
}
