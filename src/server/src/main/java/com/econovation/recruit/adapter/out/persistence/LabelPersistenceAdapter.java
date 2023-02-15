package com.econovation.recruit.adapter.out.persistence;

import com.econovation.recruit.application.port.out.LabelRecordPort;
import com.econovation.recruit.domain.label.Label;
import com.econovation.recruit.domain.label.LabelRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LabelPersistenceAdapter implements LabelRecordPort {
    private static final String NO_MATCH_LABEL_MESSAGE = "";
    private final LabelRepository labelRepository;
    @Override
    public Label save(Label label) {
        Label loadLabel = labelRepository.save(label);
        if (loadLabel.equals(null)) {
            throw new IllegalArgumentException(NO_MATCH_LABEL_MESSAGE);
        }
        return loadLabel;
    }
}
