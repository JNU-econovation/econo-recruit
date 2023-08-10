package com.econovation.recruitdomain.out;

import com.econovation.recruitdomain.domains.score.Score;

public interface ScoreRecordPort {
    Score save(Score score);
}
