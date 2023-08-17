package com.econovation.recruitdomain.out;

import com.econovation.recruitcommon.utils.Result;
import com.econovation.recruitdomain.domains.label.Label;

public interface LabelRecordPort {
    Result<Label> save(Label label);

    Boolean delete(Label label);
}
