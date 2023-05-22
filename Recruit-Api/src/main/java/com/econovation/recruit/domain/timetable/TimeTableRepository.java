package com.econovation.recruit.domain.timetable;

import com.econovation.recruit.domain.applicant.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeTableRepository extends JpaRepository<TimeTable, Integer> {
    List<TimeTable> findByApplicant(Applicant applicant);
}