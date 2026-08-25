package com.econovation.recruitdomain.domains.timetable.domain;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TimeTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "timetable_id")
    private Integer id;

    @Column(name = "applicant_id", nullable = false)
    private String applicantId;

    @Column(name = "start_time")
    private Integer startTime;
}
