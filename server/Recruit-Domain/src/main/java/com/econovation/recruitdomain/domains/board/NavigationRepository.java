package com.econovation.recruitdomain.domains.board;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NavigationRepository extends JpaRepository<Navigation, Integer> {
    Optional<Navigation> findById(Integer id);

    Optional<Navigation> findNavigationByNavLoc(Integer navLoc);
}
