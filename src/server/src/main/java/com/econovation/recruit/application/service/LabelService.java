package com.econovation.recruit.application.service;

import com.econovation.recruit.application.port.in.LabelUseCase;
import com.econovation.recruit.application.port.out.*;
import com.econovation.recruit.domain.applicant.Applicant;
import com.econovation.recruit.domain.interviewer.Interviewer;
import com.econovation.recruit.domain.label.Label;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LabelService implements LabelUseCase {
    private final LabelRecordPort labelRecordPort;
    private final LabelLoadPort labelLoadPort;
    private final LoadApplicantPort loadApplicantPort;
    @Override
    @Transactional
    public Label createLabel(Integer applicantId, Integer idpId) {
        Applicant applicant = loadApplicantPort.loadApplicantById(applicantId);
        Label label = Label.builder()
                .idpId(idpId)
                .applicant(applicant)
                .build();
        labelRecordPort.save(label);
        applicant.plusLabelCount();
        return label;
    }

    @Override
    public List<Label> findByApplicantId(Integer applicantId) {
        Applicant applicant = loadApplicantPort.loadApplicantById(applicantId);
        return labelLoadPort.loadLabelByApplicant(applicant);
    }

    @Override
    public Boolean deleteLabel(Integer applicantId, Integer idpId) {
        Applicant applicant = loadApplicantPort.loadApplicantById(applicantId);
        Label label = labelLoadPort.loadLabelByApplicantAndIdpId(applicant, idpId);
        applicant.minusLabelCount();
        return labelRecordPort.delete(label);
    }
}
