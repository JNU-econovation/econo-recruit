package com.econovation.recruitdomain.out;

import com.econovation.recruitdomain.domains.record.domain.Record;
import java.util.List;

public interface RecordLoadPort {
    List<Record> findAll();

    Record findByApplicantId(String applicantId);
}
