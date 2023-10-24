package com.econovation.recruitdomain.out;

import com.econovation.recruitdomain.domains.label.domain.Label;
import java.util.List;

public interface LabelRecordPort {
    Label save(Label label);

    void delete(Label label);

    void deleteAll(List<Label> label);
}
