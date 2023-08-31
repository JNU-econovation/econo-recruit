package com.econovation.recruitdomain.domains.label.adaptor;

import com.econovation.recruitcommon.annotation.Adaptor;
import com.econovation.recruitdomain.domains.label.domain.Label;
import com.econovation.recruitdomain.domains.label.domain.LabelRepository;
import com.econovation.recruitdomain.domains.label.exception.LabelNotFoundException;
import com.econovation.recruitdomain.out.LabelLoadPort;
import com.econovation.recruitdomain.out.LabelRecordPort;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

@Adaptor
@RequiredArgsConstructor
public class LabelAdaptor implements LabelRecordPort, LabelLoadPort {
    private static final String NO_MATCH_LABEL_MESSAGE = "";
    private final String EMPTY_LABEL_MESSAGE = "해당 지원자는 라벨이 존재하지 않습니다.";
    private final LabelRepository labelRepository;

    @Override
    public Label save(Label label) {
        return labelRepository.save(label);
    }

    @Override
    public void delete(Label label) {
        labelRepository.delete(label);
    }

    @Override
    public List<Label> loadLabelByApplicantId(String applicantId) {
        List<Label> labels = labelRepository.findByApplicantId(applicantId);
        if (labels.isEmpty()) {
            throw LabelNotFoundException.EXCEPTION;
        }
        return labels;
    }

    @Override
    public Label loadLabelByApplicantIdAndIdpId(String applicantId, Long idpId) {
        Optional<Label> label = labelRepository.findByApplicantIdAndIdpId(applicantId, idpId);
        if (label.isEmpty()) {
            return null;
            //            throw LabelNotFoundException.EXCEPTION;
        }
        return label.get();
    }

    @Override
    public List<Label> findAll() {
        return labelRepository.findAll();
    }

    @Override
    public Map<Long, Label> loadLabelByCardIdIn(List<Long> cardIds) {
        List<Label> labels = labelRepository.findByCardIdIn(cardIds);
        return labels.stream().collect(Collectors.toMap(Label::getCardId, label -> label));
    }
}
