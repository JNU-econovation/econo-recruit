package com.econovation.recruitdomain.domains.dto;

import com.econovation.recruitdomain.domains.interviewer.domain.Interviewer;
import com.econovation.recruitinfrastructure.idp.dto.InterviewerResponse;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class InterviewerCreateDto {
    private Integer idpId;
    private String role;

    public static Interviewer from(InterviewerResponse interviewerResponse) {
        return Interviewer.builder()
                .id(interviewerResponse.getIdpId())
                .name(interviewerResponse.getName())
                .year(interviewerResponse.getYear())
                .build();
    }
}
