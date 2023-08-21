package com.econovation.recruitdomain.domains.timetable.domain;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeTableRepository extends JpaRepository<TimeTable, Integer> {
    List<TimeTable> findByApplicantId(UUID applicantId);
}
