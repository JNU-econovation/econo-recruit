package com.econovation.recruit.api.score.usecase;

import com.econovation.recruitdomain.domains.dto.CreateScoreDto;
import com.econovation.recruitdomain.domains.dto.ScoreAverageDto;
import com.econovation.recruitdomain.domains.dto.ScoreVo;
import java.util.List;
import java.util.Map;

public interface ScoreUseCase {
    void createScore(CreateScoreDto scoreDto);

    void updateScore(CreateScoreDto scoreDto);

    Map<String, List<ScoreVo>> getByApplicantId(String applicantId);

    ScoreAverageDto getApplicantScoreWithAverage(String applicantId);
}
