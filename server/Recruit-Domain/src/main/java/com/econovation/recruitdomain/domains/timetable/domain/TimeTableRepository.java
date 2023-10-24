package com.econovation.recruitdomain.domains.timetable.domain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeTableRepository extends JpaRepository<TimeTable, Integer> {
    List<TimeTable> findByApplicantId(String applicantId);
}
