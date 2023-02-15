package com.econovation.recruit.application.service;

import com.econovation.recruit.application.port.in.LabelUseCase;
import com.econovation.recruit.application.port.out.LabelRecordPort;
import com.econovation.recruit.application.port.out.LoadApplicantPort;
import com.econovation.recruit.application.port.out.LoadInterviewerPort;
import com.econovation.recruit.domain.applicant.Applicant;
import com.econovation.recruit.domain.interviewer.Interviewer;
import com.econovation.recruit.domain.label.Label;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LabelService implements LabelUseCase {
    private final LabelRecordPort labelRecordPort;
    private final LoadApplicantPort loadApplicantPort;
    private final LoadInterviewerPort loadInterviewerPort;
    @Override
    public Label createLabel(Integer applicantId, Integer idpId) {
        Applicant applicant = loadApplicantPort.loadApplicantById(applicantId);
        Interviewer interviewer = loadInterviewerPort.loadInterviewById(idpId);
        Label label = Label.builder()
                .interviewer(interviewer)
                .applicant(applicant)
                .build();
        return labelRecordPort.save(label);
    }
}
