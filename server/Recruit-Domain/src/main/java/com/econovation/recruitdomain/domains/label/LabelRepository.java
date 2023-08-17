package com.econovation.recruitdomain.domains.label;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabelRepository extends JpaRepository<Label, Long> {
    List<Label> findByApplicantId(Integer applicantId);

    Optional<Label> findByApplicantIdAndIdpId(Integer applicantId, Integer idpId);
}
