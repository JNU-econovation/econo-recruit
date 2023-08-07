package com.econovation.recruitdomain.domain.score;


import com.econovation.recruitdomain.domain.applicant.Applicant;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {
    List<Score> findByApplicant(Applicant applicant);
}
