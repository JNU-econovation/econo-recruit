package com.econovation.recruit.domain.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NavigationRepository extends JpaRepository<Navigation, Long> {
    Optional<Navigation> findNavigationByNavLoc(Integer navLoc);
}
