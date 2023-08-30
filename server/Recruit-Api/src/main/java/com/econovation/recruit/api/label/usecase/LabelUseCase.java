package com.econovation.recruit.api.label.usecase;

import com.econovation.recruitcommon.annotation.UseCase;
import com.econovation.recruitdomain.domains.label.domain.Label;
import java.util.List;

@UseCase
public interface LabelUseCase {

    Label createLabel(String applicantId);

    List<String> findByApplicantId(String applicantId);

    void deleteLabel(String applicantId, Integer idpId);
}
