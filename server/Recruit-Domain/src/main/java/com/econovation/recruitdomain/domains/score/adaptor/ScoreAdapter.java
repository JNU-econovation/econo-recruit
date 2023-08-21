package com.econovation.recruitdomain.domains.score.adaptor;

import com.econovation.recruitcommon.annotation.Adaptor;
import com.econovation.recruitdomain.domains.score.domain.Score;
import com.econovation.recruitdomain.domains.score.domain.ScoreRepository;
import com.econovation.recruitdomain.out.ScoreLoadPort;
import com.econovation.recruitdomain.out.ScoreRecordPort;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@Adaptor
@RequiredArgsConstructor
public class ScoreAdapter implements ScoreLoadPort, ScoreRecordPort {
    private final ScoreRepository scoreRepository;

    @Override
    public Score save(Score score) {
        return scoreRepository.save(score);
    }

    @Override
    public List<Score> save(List<Score> scores) {
        return scoreRepository.saveAll(scores);
    }

    @Override
    public List<Score> findByApplicantId(UUID applicantId) {
        return scoreRepository.findByApplicantId(applicantId);
    }
}
