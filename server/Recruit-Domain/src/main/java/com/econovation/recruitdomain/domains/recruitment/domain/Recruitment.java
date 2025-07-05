package com.econovation.recruitdomain.domains.recruitment.domain;

import com.econovation.recruitdomain.domains.BaseTimeEntity;
import com.econovation.recruitdomain.domains.applicant.domain.state.RecruitmentStates;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Recruitment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private RecruitmentStates states;

    @Column
    private Long year;

    @Column
    private LocalDateTime startAt;

    @Column
    private LocalDateTime endAt;

}
