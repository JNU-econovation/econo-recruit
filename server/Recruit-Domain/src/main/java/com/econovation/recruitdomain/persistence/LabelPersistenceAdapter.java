package com.econovation.recruitdomain.persistence;


import com.econovation.recruitdomain.domains.applicant.Applicant;
import com.econovation.recruitdomain.domains.label.Label;
import com.econovation.recruitdomain.domains.label.LabelRepository;
import com.econovation.recruitdomain.out.LabelLoadPort;
import com.econovation.recruitdomain.out.LabelRecordPort;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LabelPersistenceAdapter implements LabelRecordPort, LabelLoadPort {
    private static final String NO_MATCH_LABEL_MESSAGE = "";
    private final String EMPTY_LABEL_MESSAGE = "해당 지원자는 라벨이 존재하지 않습니다.";
    private final LabelRepository labelRepository;

    @Override
    public Label save(Label label) {
        Label loadLabel = labelRepository.save(label);
        if (loadLabel.equals(null)) {
            throw new IllegalArgumentException(NO_MATCH_LABEL_MESSAGE);
        }
        return loadLabel;
    }

    @Override
    public Boolean delete(Label label) {
        labelRepository.delete(label);
        return true;
    }

    @Override
    public List<Label> loadLabelByApplicant(Applicant applicant) {
        List<Label> labels = labelRepository.findByApplicant(applicant);
        if (labels.isEmpty()) {
            throw new IllegalArgumentException(NO_MATCH_LABEL_MESSAGE);
        }
        return labels;
    }

    @Override
    public Label loadLabelByApplicantAndIdpId(Applicant applicant, Integer idpId) {
        labelRepository.findByApplicantAndIdpId(applicant, idpId);
        return null;
    }
}
