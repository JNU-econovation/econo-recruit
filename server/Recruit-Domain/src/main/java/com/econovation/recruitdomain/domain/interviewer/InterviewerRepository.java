package com.econovation.recruitdomain.domain.interviewer;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewerRepository extends JpaRepository<Interviewer, Integer> {}
