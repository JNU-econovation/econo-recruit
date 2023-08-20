package com.econovation.recruitdomain.domains.dto;

import java.util.List;
import java.util.UUID;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class CreateScoreDto {
    private UUID applicantId;
    private List<ScoreVo> scoreVo;
}
