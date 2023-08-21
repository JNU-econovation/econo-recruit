package com.econovation.recruitdomain.domains.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ScoreVo {
    private String creteria;
    private Float score;
}
