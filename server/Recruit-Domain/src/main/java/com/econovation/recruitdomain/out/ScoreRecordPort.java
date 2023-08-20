package com.econovation.recruitdomain.out;

import com.econovation.recruitdomain.domains.score.Score;
import java.util.List;

public interface ScoreRecordPort {
    Score save(Score score);

    List<Score> save(List<Score> scores);
}
