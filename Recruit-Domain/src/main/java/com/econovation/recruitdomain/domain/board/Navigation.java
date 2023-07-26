package com.econovation.recruitdomain.domain.board;


import javax.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Navigation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "nav_title")
    private String navTitle;

    @Column(name = "nav_loc")
    private Integer navLoc;

    public Integer getNavLoc() {
        return navLoc;
    }

    public void setNavTitle(String navTitle) {
        this.navTitle = navTitle;
    }
}
