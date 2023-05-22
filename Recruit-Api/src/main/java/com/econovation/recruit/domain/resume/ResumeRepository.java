package com.econovation.recruit.domain.resume;

import com.econovation.recruit.domain.applicant.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Integer> {
    List<Resume> findByApplicant(Applicant applicant);

}
