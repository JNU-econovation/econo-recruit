package com.econovation.recruitdomain.out;

import com.econovation.recruitcommon.utils.Result;
import com.econovation.recruitdomain.domains.label.domain.Label;

public interface LabelRecordPort {
    Result<Label> save(Label label);

    void delete(Label label);
}
