package com.econovation.recruit.api.record.usecase;

import com.econovation.recruitdomain.domains.dto.CreateRecordDto;
import com.econovation.recruitdomain.domains.record.domain.Record;
import java.util.List;
import java.util.UUID;

public interface RecordUseCase {
    Record createRecord(CreateRecordDto record);

    List<Record> findAll();

    Record findByApplicantId(UUID applicantId);
}
