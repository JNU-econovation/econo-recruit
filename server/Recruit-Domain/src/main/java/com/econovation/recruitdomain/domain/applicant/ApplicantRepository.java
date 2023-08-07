package com.econovation.recruitdomain.domain.applicant;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, Integer> {
    Boolean existsByEmail(String email);
}
