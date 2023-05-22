package com.econovation.recruit.application.port.out;

import com.econovation.recruit.domain.applicant.Applicant;
import com.econovation.recruit.domain.record.Record;

import java.util.List;

public interface RecordLoadPort {
    List<Record> findAll();

    Record findByApplicant(Applicant applicant);
}
