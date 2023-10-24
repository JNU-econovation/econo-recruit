package com.econovation.recruitdomain.domains.record.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepository extends JpaRepository<Record, Integer> {
    Optional<Record> findByApplicantId(String applicantId);
}
