package com.econovation.recruit.application.port.in;

import com.econovation.recruitdomain.domains.record.Record;
import java.util.List;
import java.util.UUID;

public interface RecordUseCase {
    Record createRecord(Record record);

    List<Record> findAll();

    Record findByApplicantId(UUID applicantId);
}
