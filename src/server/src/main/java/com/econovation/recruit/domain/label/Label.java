package com.econovation.recruit.domain.label;

import com.econovation.recruit.domain.applicant.Applicant;
import com.econovation.recruit.domain.interviewer.Interviewer;
import com.econovation.recruit.domain.interviewer.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Label {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "label_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;

    @ManyToOne
    @JoinColumn(name = "idp_id")
    private Interviewer interviewer;
}
