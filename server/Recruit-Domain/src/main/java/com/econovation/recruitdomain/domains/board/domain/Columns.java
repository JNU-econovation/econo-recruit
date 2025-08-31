package com.econovation.recruitdomain.domains.board.domain;

import com.econovation.recruitdomain.domains.BaseTimeEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Table(name = "columns")
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

    @Column(name = "year")
    private Integer year;

    public void updateLocation(Integer nextColumnsId) {
        this.nextColumnsId = nextColumnsId;
    }

    public void updateNextColumnsId(Integer id) {
        this.nextColumnsId = id;
    }

    /**
     * 공통 Navigation에 있는 컬럼을 생성하므로, navigationId 는 1로 설정됩니다.
     *
     * @param title
     * @param year
     * @return
     */
    public static Columns createCommonColumn(String title, int year) {
        return Columns.builder().navigationId(1).title(title).year(year).build();
    }
}
