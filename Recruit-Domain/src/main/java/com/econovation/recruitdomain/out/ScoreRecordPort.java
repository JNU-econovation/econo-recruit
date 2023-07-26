package com.econovation.recruitdomain.out;


import com.econovation.recruitdomain.domain.score.Score;

public interface ScoreRecordPort {
    Score save(Score score);
}
