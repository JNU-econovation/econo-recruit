package com.econovation.recruitdomain.domains.record;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepository extends JpaRepository<Record, Integer> {
    Optional<Record> findByApplicantId(UUID applicantId);
}
