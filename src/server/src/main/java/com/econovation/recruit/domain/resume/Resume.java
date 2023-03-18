package com.econovation.recruit.domain.resume;

import com.econovation.recruit.domain.applicant.Applicant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "resume_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;

    @Column(name = "question_id")
    private Integer questionId;

    @Column(name = "answer")
    private String answer;
}
