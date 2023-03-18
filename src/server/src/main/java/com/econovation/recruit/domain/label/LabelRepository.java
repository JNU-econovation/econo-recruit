package com.econovation.recruit.domain.label;

import com.econovation.recruit.domain.applicant.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LabelRepository extends JpaRepository<Label, Long> {
    List<Label> findByApplicant(Applicant applicant);
    Boolean findByApplicantAndIdpId(Applicant applicant, Integer idpId);
}
