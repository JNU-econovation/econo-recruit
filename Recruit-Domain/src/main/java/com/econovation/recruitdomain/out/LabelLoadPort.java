package com.econovation.recruitdomain.out;


import com.econovation.recruitdomain.domain.applicant.Applicant;
import com.econovation.recruitdomain.domain.label.Label;
import java.util.List;

public interface LabelLoadPort {
    List<Label> loadLabelByApplicant(Applicant applicant);

    Label loadLabelByApplicantAndIdpId(Applicant applicant, Integer idpId);
}
