package com.econovation.recruitdomain.domains.dto;

import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ScoreAverageDto {

    /**
     * { "totalAverage": 4.2, "fieldAverages": [4.2, 4.2, 4.2, 4.2], "myScore": [3, 3, 3, 3],
     * "interviewers": { "홍길동": [2, 2, 2, 2], "김철수": [2, 2, 2, 2] }
     */
    private Float totalAverage;

    private List<Float> fieldAverages;
    private List<Float> myScore;
    private Map<String, List<Float>> interviewers;

    public static ScoreAverageDto of(
            Float totalAverage,
            List<Float> fieldAverages,
            List<Float> myScore,
            Map<String, List<Float>> interviewers) {
        return new ScoreAverageDto(totalAverage, fieldAverages, myScore, interviewers);
    }
}
