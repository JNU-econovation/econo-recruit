package com.econovation.recruit.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
@AllArgsConstructor
public class ResumeInsertDto {
    private Integer applicantId;
    @Range(min = 100, max = 999)
    private Integer questionId;
    private String answer;
}