package com.econovation.recruit.application.port.in;

import com.econovation.recruit.domain.label.Label;

public interface LabelUseCase {

    Label createLabel(Integer applicantId, Integer idpId);
}
