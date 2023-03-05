package com.econovation.recruit.domain.dto;

import com.econovation.recruit.domain.applicant.Applicant;
import lombok.Data;
import lombok.Getter;

import javax.persistence.Column;

@Data
@Getter
public class CreateScoreDto {
    private Integer applicantId;
    private String criteria;
    private Float score;
    private Integer idpId;
}
