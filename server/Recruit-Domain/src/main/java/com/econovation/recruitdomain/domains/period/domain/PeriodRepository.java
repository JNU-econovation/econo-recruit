package com.econovation.recruitdomain.domains.period.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PeriodRepository extends JpaRepository<Period, Long> {

    @Query("SELECT p FROM Period p ORDER BY p.createdAt DESC")
    Optional<Period> findLatestOne();
}
