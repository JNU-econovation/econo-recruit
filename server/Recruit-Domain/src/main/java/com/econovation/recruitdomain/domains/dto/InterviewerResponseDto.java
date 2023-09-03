package com.econovation.recruitdomain.domains.dto;

import com.econovation.recruitdomain.domains.interviewer.domain.Interviewer;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@Builder
public class InterviewerResponseDto {
    private Long id;
    private String name;
    private Integer year;
    private String email;
    private String role;

    public static InterviewerResponseDto from(Interviewer interviewer) {
        return InterviewerResponseDto.builder()
                .id(interviewer.getId())
                .name(interviewer.getName())
                .year(interviewer.getYear())
                .email(interviewer.getEmail())
                .role(interviewer.getRole().toString())
                .build();
    }
}
