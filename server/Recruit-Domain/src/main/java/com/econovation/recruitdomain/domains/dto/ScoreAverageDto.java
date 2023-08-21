package com.econovation.recruitdomain.domains.dto;

import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ScoreAverageDto {

    private Float totalAverage;
    private Map<String, List<ScoreVo>> scoreVo;

    public static ScoreAverageDto of(Float totalAverage, Map<String, List<ScoreVo>> scoreVo) {
        return new ScoreAverageDto(totalAverage, scoreVo);
    }
}
