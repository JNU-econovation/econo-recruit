package com.econovation.recruitdomain.out;

import com.econovation.recruitdomain.domains.label.Label;
import java.util.List;

public interface LabelLoadPort {
    List<Label> loadLabelByApplicantId(UUID applicantId);

    Label loadLabelByApplicantIdAndIdpId(UUID applicantId, Integer idpId);
}
