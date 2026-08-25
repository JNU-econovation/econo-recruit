package com.econovation.recruitdomain.domains.board.domain;

import com.econovation.recruitdomain.domains.BaseTimeEntity;
import javax.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Navigation extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "navigation_id")
    private Integer id;

    @Column(name = "title")
    private String navTitle;

    public void setNavTitle(String navTitle) {
        this.navTitle = navTitle;
    }
}
