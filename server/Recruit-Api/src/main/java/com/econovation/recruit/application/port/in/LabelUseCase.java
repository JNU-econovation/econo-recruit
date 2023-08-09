package com.econovation.recruit.application.port.in;

import com.econovation.recruitdomain.domains.label.Label;
import java.util.List;

public interface LabelUseCase {

    Label createLabel(Integer applicantId, Integer idpId);

    List<Label> findByApplicantId(Integer applicantId);

    Boolean deleteLabel(Integer applicantId, Integer idpId);
}
