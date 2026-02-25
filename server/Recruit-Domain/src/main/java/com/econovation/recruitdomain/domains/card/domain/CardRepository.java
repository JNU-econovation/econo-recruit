package com.econovation.recruitdomain.domains.card.domain;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    Optional<Card> findByApplicantId(String applicantId);

    @Query("SELECT c.id FROM Card c WHERE c.applicantId IN :applicantIds")
    List<Long> findAllCardIdByApplicantIdIn(@Param("applicantIds") List<String> applicantIds);

    void deleteAllByApplicantIdIn(List<String> applicantIds);
}
