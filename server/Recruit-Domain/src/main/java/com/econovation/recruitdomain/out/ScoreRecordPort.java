package com.econovation.recruitdomain.out;

import com.econovation.recruitdomain.domains.score.domain.Score;
import java.util.List;

public interface ScoreRecordPort {
    Score save(Score score);

    List<Score> save(List<Score> scores);

    void deleteByInterviewerId(Long idpId);
}
