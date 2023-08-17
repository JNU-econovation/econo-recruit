package com.econovation.recruitdomain.domains.dto;

import com.econovation.recruitdomain.domains.interviewer.domain.Interviewer;
import com.econovation.recruitdomain.domains.interviewer.domain.Role;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class InterviewerCreateDto {
    private Integer idpId;
    private String role;

    public Interviewer from() {
        return Interviewer.builder().id(idpId).role(Role.valueOf(role)).build();
    }
}
