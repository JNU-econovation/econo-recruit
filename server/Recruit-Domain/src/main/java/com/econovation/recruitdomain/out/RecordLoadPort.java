package com.econovation.recruitdomain.out;

import com.econovation.recruitdomain.domains.record.domain.Record;
import java.util.List;
import java.util.UUID;

public interface RecordLoadPort {
    List<Record> findAll();

    Record findByApplicantId(UUID applicantId);
}
