package com.econovation.recruitdomain.out;

import com.econovation.recruitdomain.domains.label.domain.Label;
import java.util.List;
import java.util.Map;

public interface LabelLoadPort {
    List<Label> loadLabelByApplicantId(String applicantId);

    Label loadLabelByApplicantIdAndIdpId(String applicantId, Long idpId);

    List<Label> findAll();

    List<Label> loadLabelByCardIdIn(List<Long> cardIds);
}
