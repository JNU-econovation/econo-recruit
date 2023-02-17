package com.econovation.recruit.application.service;

import com.econovation.recruit.application.port.in.LabelUseCase;
import com.econovation.recruit.application.port.out.LabelRecordPort;
import com.econovation.recruit.application.port.out.LoadApplicantPort;
import com.econovation.recruit.application.port.out.LoadInterviewerPort;
import com.econovation.recruit.application.port.out.RecordApplicantPort;
import com.econovation.recruit.domain.applicant.Applicant;
import com.econovation.recruit.domain.interviewer.Interviewer;
import com.econovation.recruit.domain.label.Label;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LabelService implements LabelUseCase {
    private final LabelRecordPort labelRecordPort;
    private final LoadApplicantPort loadApplicantPort;
    private final RecordApplicantPort recordApplicantPort;
    private final LoadInterviewerPort loadInterviewerPort;
    @Override
    @Transactional
    public Label createLabel(Integer applicantId, Integer idpId) {
        Applicant applicant = loadApplicantPort.loadApplicantById(applicantId);
        Interviewer interviewer = loadInterviewerPort.loadInterviewById(idpId);
        Label label = Label.builder()
                .interviewer(interviewer)
                .applicant(applicant)
                .build();
        labelRecordPort.save(label);
        applicant.plusLabelCount();
        return label;
    }
}
