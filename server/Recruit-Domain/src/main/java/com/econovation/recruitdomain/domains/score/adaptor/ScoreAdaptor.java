package com.econovation.recruitdomain.domains.score.adaptor;

import com.econovation.recruitcommon.annotation.Adaptor;
import com.econovation.recruitdomain.domains.score.domain.Score;
import com.econovation.recruitdomain.domains.score.domain.ScoreRepository;
import com.econovation.recruitdomain.out.ScoreLoadPort;
import com.econovation.recruitdomain.out.ScoreRecordPort;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Adaptor
@RequiredArgsConstructor
public class ScoreAdaptor implements ScoreLoadPort, ScoreRecordPort {
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
    public List<Score> findByApplicantId(String applicantId) {
        return scoreRepository.findByApplicantId(applicantId);
    }

    @Override
    public List<Score> findByApplicantIds(List<String> applicantIds) {
        return scoreRepository.findByApplicantIdIn(applicantIds);
    }
}
