package com.econovation.recruitdomain.out;

import com.econovation.recruitdomain.domains.label.Label;
import java.util.List;

public interface LabelLoadPort {
    List<Label> loadLabelByApplicantId(Integer applicantId);

    Label loadLabelByApplicantIdAndIdpId(Integer applicantId, Integer idpId);
}
