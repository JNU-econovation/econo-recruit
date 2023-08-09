package com.econovation.recruitdomain.domains.record;

import com.econovation.recruitdomain.domains.applicant.Applicant;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "record_id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;

    @Column(name = "url")
    private String url;

    @Column(name = "record")
    private String record;
}
