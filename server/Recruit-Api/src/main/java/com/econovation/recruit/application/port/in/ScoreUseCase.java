package com.econovation.recruit.application.port.in;


import com.econovation.recruitdomain.domain.score.Score;
import java.util.List;

public interface ScoreUseCase {
    Score createScore(Score score);

    List<Score> getByApplicantId(Integer applicantId);
}
