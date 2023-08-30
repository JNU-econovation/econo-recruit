package com.econovation.recruitdomain.domains.applicant.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApplicantPdfDto {
    private String field;
    private String field1;
    private String field2;
    private String name;
    private String contacted;
    private String classOf;
    private String grade;
    private String semester;
    private String major;
    private String doubleMajor;
    private String minor;
    private String activity;
    private String channel;
    private String reason;
    private String future;
    private String experience;
    private String experienceTextarea;
    private String goal;
    private String deep;
    private String collaboration;
    private String portfolio;
    private String fileUrl;
    private String fileUrlforPlanner;
    private String email;
    private String check;
    private String personalInformationAgree;
    private String personalInformationAgreeForPortfolio;
}
