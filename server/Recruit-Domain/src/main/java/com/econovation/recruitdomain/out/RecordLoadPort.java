package com.econovation.recruitdomain.out;

import com.econovation.recruitdomain.domains.record.domain.Record;
import java.util.List;
import java.util.Optional;

public interface RecordLoadPort {
    List<Record> findAll();

    List<Record> findAll(Integer page);

    Optional<Record> findByApplicantId(String applicantId);

    Long getTotalCount();
}
