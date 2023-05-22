package com.econovation.recruit.domain.dto;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class UpdateScoreDto {
    private Integer scoreId;
    private Integer applicantId;
    private String criteria;
    private Float score;

}
