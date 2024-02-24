package com.econovation.recruitdomain.domains.interviewer.domain;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewerRepository extends JpaRepository<Interviewer, Long> {
    Optional<Interviewer> findByEmail(String email);

    List<Interviewer> findByRole(Role role);
}
