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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
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

    public Recruitment updateStates(RecruitmentStates states){
        this.states = states;
        return this;
    }

}
