package com.econovation.recruitdomain.domains.interviewer.domain;

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
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "year")
    private Integer year;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    public void changeRole(Role role) {
        this.role = role;
    }
}
