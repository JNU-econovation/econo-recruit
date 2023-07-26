package com.econovation.recruitdomain.domain.resume;


import com.econovation.recruitdomain.domain.applicant.Applicant;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Integer> {
    List<Resume> findByApplicant(Applicant applicant);
}
