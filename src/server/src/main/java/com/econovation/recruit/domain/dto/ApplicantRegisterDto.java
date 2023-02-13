package com.econovation.recruit.domain.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;

@Data
public class ApplicantRegisterDto {
    private String hopeField;
    private String firstPriority;
    private String secondPriority;
    private String name;
    private String phoneNumber;
    @Email
    private String email;
    @Range(min=0, max=1000000)
    private Integer studentId;
    @Range(min=1, max=6)
    private Integer grade;
    private String semester;
    private String major;
    private String doubleMajor;
    private String minor;
    private String likeCount;
    private String commentCount;
}
