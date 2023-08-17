package com.econovation.recruit.application.port.in;

import com.econovation.recruitcommon.annotation.UseCase;
import com.econovation.recruitdomain.domains.label.Label;
import java.util.List;

@UseCase
public interface LabelUseCase {

    Label createLabel(Integer applicantId, Integer idpId);

    List<String> findByApplicantId(Integer applicantId);

    void deleteLabel(Integer applicantId, Integer idpId);
}
