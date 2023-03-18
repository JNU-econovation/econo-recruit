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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "label_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;

    @Column(name = "idpId")
    private Integer idpId;
}
