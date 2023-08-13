package com.econovation.recruitdomain.domains.board.domain;

import com.econovation.recruitdomain.domains.BaseTimeEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Columns extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "navigation_id")
    private Integer navigationId;

    @Column(name = "prev_col_loc")
    private Integer prevColLoc;

    @Column(name = "next_col_loc")
    private Integer nextColLoc;

    public void updateLocation(Integer prevColLoc, Integer nextColLoc) {
        this.prevColLoc = prevColLoc;
        this.nextColLoc = nextColLoc;
    }
}
