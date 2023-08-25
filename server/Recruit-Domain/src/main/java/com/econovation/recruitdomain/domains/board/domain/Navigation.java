package com.econovation.recruitdomain.domains.board.domain;

import javax.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Navigation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nav_title")
    private String navTitle;

    public void setNavTitle(String navTitle) {
        this.navTitle = navTitle;
    }
}
