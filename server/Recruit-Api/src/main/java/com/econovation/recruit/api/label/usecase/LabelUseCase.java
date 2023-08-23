package com.econovation.recruit.api.label.usecase;

import com.econovation.recruitcommon.annotation.UseCase;
import com.econovation.recruitdomain.domains.label.Label;
import java.util.List;

@UseCase
public interface LabelUseCase {

    Label createLabel(UUID applicantId);

    List<String> findByApplicantId(UUID applicantId);

    void deleteLabel(UUID applicantId, Integer idpId);
}
