package com.econovation.recruitdomain.domain.dto;


import javax.validation.constraints.Email;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class ApplicantRegisterDto {
    private String hopeField;
    private String firstPriority;
    private String secondPriority;
    private String name;
    private String phoneNumber;
    @Email private String email;

    @Range(min = 99999, max = 1000000)
    private Integer studentId;

    @Range(min = 1, max = 6)
    private Integer grade;

    private Integer semester;
    private String major;
    private String doubleMajor;
    private String minor;
    private String portfolio;
    private String supportPath;
    private String plan;
}
