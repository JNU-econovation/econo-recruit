package com.econovation.recruitdomain.out;

import com.econovation.recruitdomain.domains.label.domain.Label;
import java.util.List;
import java.util.UUID;

public interface LabelLoadPort {
    List<Label> loadLabelByApplicantId(UUID applicantId);

    Label loadLabelByApplicantIdAndIdpId(UUID applicantId, Integer idpId);
}
