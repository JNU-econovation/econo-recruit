package com.econovation.recruitdomain.domains.card.domain;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findAllByBoardIdIn(List<Integer> boardIds);

    Optional<Card> findByApplicantId(UUID applicantId);
}
