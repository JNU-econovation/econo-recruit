package com.econovation.recruitdomain.persistence;


import com.econovation.recruitdomain.domain.applicant.Applicant;
import com.econovation.recruitdomain.domain.score.Score;
import com.econovation.recruitdomain.domain.score.ScoreRepository;
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
    public List<Score> findByApplicant(Applicant applicant) {
        return scoreRepository.findByApplicant(applicant);
    }
}
