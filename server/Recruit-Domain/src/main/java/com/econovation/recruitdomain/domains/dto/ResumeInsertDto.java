package com.econovation.recruitdomain.domains.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
@AllArgsConstructor
public class ResumeInsertDto {
    private Integer applicantId;

    @Range(min = 1000, max = 9999)
    private Integer questionId;

    private String answer;
}
