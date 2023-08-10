package com.econovation.recruitdomain.domains.label;

import com.econovation.recruitdomain.domains.applicant.Applicant;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

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
