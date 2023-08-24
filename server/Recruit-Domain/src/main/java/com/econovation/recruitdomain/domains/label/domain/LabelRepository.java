package com.econovation.recruitdomain.domains.label.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabelRepository extends JpaRepository<Label, Long> {
    List<Label> findByApplicantId(UUID applicantId);

    Optional<Label> findByApplicantIdAndIdpId(UUID applicantId, Integer idpId);
}
