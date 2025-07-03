package com.econovation.recruitdomain.domains.recruitment.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {

    @Query("SELECT r FROM Recruitment r ORDER BY r.updatedAt DESC LIMIT 1")
    Optional<Recruitment> findLatestOne();

}
