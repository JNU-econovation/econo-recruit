package com.econovation.recruitdomain.domain.label;


import com.econovation.recruitdomain.domain.applicant.Applicant;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabelRepository extends JpaRepository<Label, Long> {
    List<Label> findByApplicant(Applicant applicant);

    Boolean findByApplicantAndIdpId(Applicant applicant, Integer idpId);
}
