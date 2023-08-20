package com.econovation.recruit.application.port.in;

import com.econovation.recruitdomain.domains.dto.CreateScoreDto;
import com.econovation.recruitdomain.domains.score.Score;
import java.util.List;

public interface ScoreUseCase {
    void createScore(CreateScoreDto scoreDto);

    List<Score> getByApplicantId(Integer applicantId);
}
