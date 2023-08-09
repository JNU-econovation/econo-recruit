package com.econovation.recruitdomain.domains.timetable;

import java.time.LocalDateTime;
import java.util.UUID;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "timetable_id")
    private Integer id;

    @Column(name = "applicant_id")
    private UUID applicantID;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;
}
