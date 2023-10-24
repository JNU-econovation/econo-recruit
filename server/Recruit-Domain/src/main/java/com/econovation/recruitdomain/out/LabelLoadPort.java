package com.econovation.recruitdomain.out;

import com.econovation.recruitdomain.domains.label.domain.Label;
import java.util.List;

public interface LabelLoadPort {
    List<Label> loadLabelByApplicantId(String applicantId);

    Label loadLabelByApplicantIdAndIdpId(String applicantId, Long idpId);

    List<Label> findAll();

    List<Label> loadLabelByCardIdIn(List<Long> cardIds);

    Label loadLabelByCardIdAndIdpId(Long cardId, Long idpId);

    List<Label> loadLabelByCardId(Long cardId);
}
