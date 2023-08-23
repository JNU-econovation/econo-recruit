package com.econovation.recruitdomain.domains.dto;

import com.econovation.recruitdomain.domains.interviewer.domain.Interviewer;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class InterviewerResponse {
    private Long idpId;
    private String name;
    private String email;
    private Integer year;

    public static Interviewer from(InterviewerResponse interviewerResponse) {
        return Interviewer.builder()
                .id(interviewerResponse.getIdpId())
                .name(interviewerResponse.getName())
                //                .email(interviewerResponse.getEmail())
                .year(interviewerResponse.getYear())
                .build();
    }
}
