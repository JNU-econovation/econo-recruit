package com.econovation.recruit.domain.timetable;

import com.econovation.recruit.domain.applicant.Applicant;
import com.econovation.recruit.domain.interviewer.Role;

import javax.persistence.*;

@Entity
public class TimeTable {
    @Id
    @Column(name = "timetable_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "end_time")
    private String endTime;

    @Column(name = "start_time")
    private String day;
}
