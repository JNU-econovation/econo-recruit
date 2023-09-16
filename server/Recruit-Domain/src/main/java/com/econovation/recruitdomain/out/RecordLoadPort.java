package com.econovation.recruitdomain.out;

import com.econovation.recruitdomain.domains.record.domain.Record;
import java.util.List;
import java.util.Optional;

public interface RecordLoadPort {
    List<Record> findAll();

    Optional<Record> findByApplicantId(String applicantId);
}
