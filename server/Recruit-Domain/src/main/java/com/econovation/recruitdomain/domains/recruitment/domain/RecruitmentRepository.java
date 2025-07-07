package com.econovation.recruitdomain.domains.recruitment.domain;

import com.econovation.recruitdomain.domains.applicant.domain.state.RecruitmentStates;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {

    @Query("SELECT r FROM Recruitment r ORDER BY r.updatedAt DESC")
    List<Recruitment> findLatestOne();

    @Query("SELECT r FROM Recruitment r WHERE r.states=:states ORDER BY r.updatedAt DESC")
    List<Recruitment> findByStates(@Param("states") RecruitmentStates states);
}
