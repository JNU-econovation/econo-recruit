package com.econovation.recruit.application.port.in;

import com.econovation.recruit.domain.record.Record;

import java.util.List;

public interface RecordUseCase {
    Record createRecord(Record record);
    List<Record> findAll();

    Record findByApplicantId(Integer applicantId);
}
