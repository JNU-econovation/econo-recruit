package com.econovation.recruitdomain.domain.score;


import com.econovation.recruitdomain.domain.applicant.Applicant;
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

    @ManyToOne
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;

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
