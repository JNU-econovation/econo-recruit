package com.econovation.recruit.domain.score;

import com.econovation.recruit.domain.applicant.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Integer> {
    List<Score> findByApplicant(Applicant applicant);
//    List<Record> findByApplicant(Applicant applicant);
//    Boolean findByApplicantAndIdpId(Applicant applicant, Integer idpId);
}
