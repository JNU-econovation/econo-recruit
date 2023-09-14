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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idp_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "year")
    private Integer year;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    //    @PrePersist
    //    public void prePersist() {
    //        this.role = Role.ROLE_GUEST;
    //    }

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

    public void changePassword(String encededPassword) {
        this.password = encededPassword;
    }
}
