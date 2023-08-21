package com.econovation.recruit.application.port.in;

import com.econovation.recruitdomain.domains.dto.CreateScoreDto;
import com.econovation.recruitdomain.domains.score.Score;
import java.util.List;
import java.util.UUID;

public interface ScoreUseCase {
    void createScore(CreateScoreDto scoreDto);

    void updateScore(CreateScoreDto scoreDto);

    List<Score> getByApplicantId(UUID applicantId);
}
