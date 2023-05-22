package com.econovation.recruit.domain.score;

import com.econovation.recruit.domain.applicant.Applicant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "score_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;

    @Column(name = "criteria")
    private String criteria;

    @Column(name = "score")
    private Float score;

    @Column(name = "idpId")
    private Integer idpId;


    public Score updateScore(String criteria, Float score) {
        this.criteria = criteria;
        this.score = score;
        return this;
    }

}
