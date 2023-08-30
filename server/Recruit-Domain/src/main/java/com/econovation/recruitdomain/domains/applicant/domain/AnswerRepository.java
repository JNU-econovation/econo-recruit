package com.econovation.recruitdomain.domains.applicant.domain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByApplicantIdIn(List<String> applicantIds);

    List<Answer> findByApplicantId(String applicantId);
}