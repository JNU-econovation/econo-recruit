package com.econovation.recruitdomain.domains.label.domain;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabelRepository extends JpaRepository<Label, Long> {
    List<Label> findByApplicantId(String applicantId);

    Optional<Label> findByApplicantIdAndIdpId(String applicantId, Long idpId);

    List<Label> findByCardIdIn(List<Long> cardIds);

    Optional<Label> findByCardIdAndIdpId(Long cardId, Long idpId);

    List<Label> findByCardId(Long cardId);
}
