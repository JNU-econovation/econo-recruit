package com.econovation.recruitdomain.domains.score.domain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {
    List<Score> findByApplicantId(String ApplicantId);

    List<Score> findByApplicantIdIn(List<String> applicantIds);

    @Modifying
    @Query("delete from Score s where s.idpId = :idpId")
    void deleteByIdpId(@Param("idpId") Long idpId);
}
