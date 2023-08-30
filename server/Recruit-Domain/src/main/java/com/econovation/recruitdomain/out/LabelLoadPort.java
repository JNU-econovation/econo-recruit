package com.econovation.recruitdomain.out;

import com.econovation.recruitdomain.domains.label.domain.Label;
import java.util.List;

public interface LabelLoadPort {
    List<Label> loadLabelByApplicantId(String applicantId);

    Label loadLabelByApplicantIdAndIdpId(String applicantId, Integer idpId);
}
