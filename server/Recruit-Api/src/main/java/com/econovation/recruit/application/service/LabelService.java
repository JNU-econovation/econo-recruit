package com.econovation.recruit.application.service;


import com.econovation.recruit.application.port.in.LabelUseCase;
import com.econovation.recruitdomain.domain.applicant.Applicant;
import com.econovation.recruitdomain.domain.label.Label;
import com.econovation.recruitdomain.out.ApplicantLoadPort;
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
    private final ApplicantLoadPort applicantLoadPort;

    @Override
    @Transactional
    public Label createLabel(Integer applicantId, Integer idpId) {
        Applicant applicant = applicantLoadPort.loadApplicantById(applicantId);
        Label label = Label.builder().idpId(idpId).applicant(applicant).build();
        labelRecordPort.save(label);
        applicant.plusLabelCount();
        return label;
    }

    @Override
    public List<Label> findByApplicantId(Integer applicantId) {
        Applicant applicant = applicantLoadPort.loadApplicantById(applicantId);
        return labelLoadPort.loadLabelByApplicant(applicant);
    }

    @Override
    public Boolean deleteLabel(Integer applicantId, Integer idpId) {
        Applicant applicant = applicantLoadPort.loadApplicantById(applicantId);
        Label label = labelLoadPort.loadLabelByApplicantAndIdpId(applicant, idpId);
        applicant.minusLabelCount();
        return labelRecordPort.delete(label);
    }
}
