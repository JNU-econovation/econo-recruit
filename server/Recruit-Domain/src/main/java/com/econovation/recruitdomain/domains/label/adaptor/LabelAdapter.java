package com.econovation.recruitdomain.domains.label.adaptor;

import com.econovation.recruitcommon.annotation.Adaptor;
import com.econovation.recruitcommon.utils.Result;
import com.econovation.recruitdomain.domains.label.domain.Label;
import com.econovation.recruitdomain.domains.label.domain.LabelRepository;
import com.econovation.recruitdomain.domains.label.exception.LabelNotFoundException;
import com.econovation.recruitdomain.out.LabelLoadPort;
import com.econovation.recruitdomain.out.LabelRecordPort;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@Adaptor
@RequiredArgsConstructor
public class LabelAdapter implements LabelRecordPort, LabelLoadPort {
    private static final String NO_MATCH_LABEL_MESSAGE = "";
    private final String EMPTY_LABEL_MESSAGE = "해당 지원자는 라벨이 존재하지 않습니다.";
    private final LabelRepository labelRepository;

    @Override
    public Result<Label> save(Label label) {
        Label loadLabel = labelRepository.save(label);
        return Result.of(loadLabel);
    }

    @Override
    public void delete(Label label) {
        labelRepository.delete(label);
    }

    @Override
    public List<Label> loadLabelByApplicantId(UUID applicantId) {
        List<Label> labels = labelRepository.findByApplicantId(applicantId);
        if (labels.isEmpty()) {
            throw new IllegalArgumentException(NO_MATCH_LABEL_MESSAGE);
        }
        return labels;
    }

    @Override
    public Label loadLabelByApplicantIdAndIdpId(UUID applicantId, Integer idpId) {
        Optional<Label> label = labelRepository.findByApplicantIdAndIdpId(applicantId, idpId);
        if (label.isEmpty()) {
            throw LabelNotFoundException.EXCEPTION;
        }
        return label.get();
    }
}
