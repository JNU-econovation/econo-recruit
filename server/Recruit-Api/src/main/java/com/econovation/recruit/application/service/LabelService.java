package com.econovation.recruit.application.service;

import com.econovation.recruit.application.port.in.LabelUseCase;
import com.econovation.recruitdomain.domains.label.Label;
import com.econovation.recruitdomain.out.LabelLoadPort;
import com.econovation.recruitdomain.out.LabelRecordPort;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LabelService implements LabelUseCase {
    private final LabelRecordPort labelRecordPort;
    private final LabelLoadPort labelLoadPort;

    @Override
    @Transactional
    public Label createLabel(Integer applicantId, Integer idpId) {
        Label label = Label.builder().idpId(idpId).applicantId(applicantId).build();
        labelRecordPort.save(label);
        // TODO: Card LabelCount 증가
        return label;
    }

    @Override
    public List<Label> findByApplicantId(Integer applicantId) {
        return labelLoadPort.loadLabelByApplicantId(applicantId);
    }

    @Override
    public Boolean deleteLabel(Integer applicantId, Integer idpId) {
        Label label = labelLoadPort.loadLabelByApplicantIdAndIdpId(applicantId, idpId);
        // TODO: Card LabelCount 감소
        return labelRecordPort.delete(label);
    }
}
