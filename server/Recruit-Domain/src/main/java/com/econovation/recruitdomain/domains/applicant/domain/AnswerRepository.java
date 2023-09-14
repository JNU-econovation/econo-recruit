package com.econovation.recruitdomain.domains.applicant.domain;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByApplicantIdIn(List<String> applicantIds);

    List<Answer> findByApplicantId(String applicantId);

    Page<Answer> findAll(Pageable pageable);

    Optional<Answer> findByAnswer(String name);

    @Query("SELECT a FROM Answer a GROUP BY a.applicantId")
    List<Answer> findGroupedAnswersByApplicantId();
}
