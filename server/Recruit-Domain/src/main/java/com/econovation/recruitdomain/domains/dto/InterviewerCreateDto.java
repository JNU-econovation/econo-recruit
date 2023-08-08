package com.econovation.recruitdomain.domains.dto;


import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class InterviewerCreateDto {
    private Integer idpId;
    private String role;
}
