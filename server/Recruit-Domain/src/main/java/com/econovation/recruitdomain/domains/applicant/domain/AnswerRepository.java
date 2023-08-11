package com.econovation.recruitdomain.domains.applicant.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    List<Answer> findByApplicantIdIn(List<UUID> applicantIds);
    List<Answer> findByApplicantId(UUID applicantId);
}
