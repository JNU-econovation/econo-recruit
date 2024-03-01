package com.econovation.recruit.api.score.usecase;

import com.econovation.recruitdomain.domains.dto.CreateScoreDto;
import com.econovation.recruitdomain.domains.dto.ScoreAverageDto;
import com.econovation.recruitdomain.domains.score.domain.Score;
import java.util.List;
import java.util.Map;

public interface ScoreUseCase {
    void createScore(CreateScoreDto scoreDto);

    void updateScore(CreateScoreDto scoreDto);

    Map<String, List<Float>> getByApplicantId(
            String applicantId, List<Score> scores, Map<Long, String> interviewers);

    ScoreAverageDto getApplicantScoreWithAverage(String applicantId);
}
