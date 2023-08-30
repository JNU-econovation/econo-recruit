package com.econovation.recruitdomain.domains.interviewer.domain;

import com.econovation.recruitinfrastructure.idp.dto.InterviewerResponse;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Interviewer {
    @Id
    @Column(name = "idp_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "year")
    private Integer year;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    public void updateRole(Role role) {
        this.role = role;
    }

    public static Interviewer from(InterviewerResponse interviewerResponse) {
        return Interviewer.builder()
                .id(interviewerResponse.getIdpId())
                .name(interviewerResponse.getName())
                //                .email(interviewerResponse.getEmail())
                .year(interviewerResponse.getYear())
                .build();
    }
}