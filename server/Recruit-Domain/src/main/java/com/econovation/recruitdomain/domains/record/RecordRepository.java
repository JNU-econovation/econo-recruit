package com.econovation.recruitdomain.domains.record;


import com.econovation.recruitdomain.domains.applicant.Applicant;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepository extends JpaRepository<Record, Integer> {
    Optional<Record> findByApplicant(Applicant applicant);
    //    List<Record> findByApplicant(Applicant applicant);
    //    Boolean findByApplicantAndIdpId(Applicant applicant, Integer idpId);
}
