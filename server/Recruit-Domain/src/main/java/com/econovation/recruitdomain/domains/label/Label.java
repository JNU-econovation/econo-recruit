package com.econovation.recruitdomain.domains.label;

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

    @Column(name = "applicant_id")
    private Integer applicantId;

    @Column(name = "idpId")
    private Integer idpId;
}
