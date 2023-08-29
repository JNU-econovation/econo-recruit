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
    @Column(name = "columns_id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "navigation_id")
    private Integer navigationId;

    @Column(name = "next_Columns_id")
    private Integer nextColumnsId;

    public void updateLocation(Integer nextColumnsId) {
        this.nextColumnsId = nextColumnsId;
    }

    public void updateNextColumnsId(Integer id) {
        this.nextColumnsId = id;
    }
}
