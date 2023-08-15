package com.econovation.recruitdomain.domains.score;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "score")
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "score_id")
    private Long id;

    @Column(name = "applicant_id")
    private Integer applicantId;

    @Column(name = "criteria")
    private String criteria;

    @Column(name = "score")
    private Float score;

    @Column(name = "idp_id")
    private Integer idpId;

    public Score updateScore(String criteria, Float score) {
        this.criteria = criteria;
        this.score = score;
        return this;
    }
}
