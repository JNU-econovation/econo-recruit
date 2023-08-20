package com.econovation.recruit.api.label.usecase;

import com.econovation.recruitcommon.annotation.UseCase;
import com.econovation.recruitdomain.domains.label.Label;
import java.util.List;

@UseCase
public interface LabelUseCase {

    Label createLabel(Integer applicantId);

    List<String> findByApplicantId(Integer applicantId);

    void deleteLabel(Integer applicantId, Integer idpId);
}
