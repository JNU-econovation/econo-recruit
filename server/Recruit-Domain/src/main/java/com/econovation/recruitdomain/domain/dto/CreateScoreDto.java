package com.econovation.recruitdomain.domain.dto;


import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class CreateScoreDto {
    private Integer applicantId;
    private String criteria;
    private Float score;
    private Integer idpId;
}
