package com.econovation.recruit.domain.record;

import com.econovation.recruit.domain.applicant.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecordRepository extends JpaRepository<Record, Integer> {
    Optional<Record> findByApplicant(Applicant applicant);
//    List<Record> findByApplicant(Applicant applicant);
//    Boolean findByApplicantAndIdpId(Applicant applicant, Integer idpId);
}
