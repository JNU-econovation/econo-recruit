package com.econovation.recruit.adapter.out.persistence;

import com.econovation.recruit.domain.applicant.Applicant;
import lombok.Data;

import javax.persistence.*;

@Data
public class ResumeInsertDto {

    private Integer applicantId;

    private Integer questionId;

    private String answer;
}
