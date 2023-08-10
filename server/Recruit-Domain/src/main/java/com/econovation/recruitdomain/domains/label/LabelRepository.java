package com.econovation.recruitdomain.domains.label;

import com.econovation.recruitdomain.domains.applicant.Applicant;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabelRepository extends JpaRepository<Label, Long> {
    List<Label> findByApplicant(Applicant applicant);

    Boolean findByApplicantAndIdpId(Applicant applicant, Integer idpId);
}
