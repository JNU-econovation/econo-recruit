package com.econovation.recruit.application.port.out;

import com.econovation.recruit.domain.label.Label;

public interface LabelRecordPort {
    Label save(Label label);
}
