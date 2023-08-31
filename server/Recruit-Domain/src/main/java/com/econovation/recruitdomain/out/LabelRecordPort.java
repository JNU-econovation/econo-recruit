package com.econovation.recruitdomain.out;

import com.econovation.recruitdomain.domains.label.domain.Label;

public interface LabelRecordPort {
    Label save(Label label);

    void delete(Label label);
}
