package com.econovation.recruit.application.port.out;

import com.econovation.recruit.domain.score.Score;

public interface ScoreRecordPort {
    Score save(Score score);
}
