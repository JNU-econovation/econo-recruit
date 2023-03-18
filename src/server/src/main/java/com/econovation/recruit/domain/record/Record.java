package com.econovation.recruit.domain.record;

import com.econovation.recruit.domain.applicant.Applicant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
