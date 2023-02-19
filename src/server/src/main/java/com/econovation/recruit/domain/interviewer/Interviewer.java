package com.econovation.recruit.domain.interviewer;

import com.econovation.recruit.domain.applicant.Applicant;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Interviewer {
    @Id
    @Column(name = "idp_id")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    public void changeRole(Role role) {
        this.role = role;
    }
}