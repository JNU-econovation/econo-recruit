package com.econovation.recruit.application.port.out;

import com.econovation.recruit.domain.applicant.Applicant;
import com.econovation.recruit.domain.label.Label;

import java.util.List;

public interface LabelLoadPort {
    List<Label> loadLabelByApplicant(Applicant applicant);

    Label loadLabelByApplicantAndIdpId(Applicant applicant, Integer idpId);
}
