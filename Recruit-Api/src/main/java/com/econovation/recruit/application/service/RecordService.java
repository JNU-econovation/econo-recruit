package com.econovation.recruit.application.service;

import com.econovation.recruit.application.port.in.RecordUseCase;
import com.econovation.recruit.application.port.out.ApplicantLoadPort;
import com.econovation.recruit.application.port.out.RecordLoadPort;
import com.econovation.recruit.application.port.out.RecordRecordPort;
import com.econovation.recruit.domain.applicant.Applicant;
import com.econovation.recruit.domain.record.Record;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecordService implements RecordUseCase {
    private final RecordRecordPort recordRecordPort;
    private final RecordLoadPort recordLoadPort;

    private final ApplicantLoadPort applicantLoadPort;
    @Override
    public Record createRecord(Record record) {
        return recordRecordPort.save(record);
    }

    @Override
    public List<Record> findAll() {
        return recordLoadPort.findAll();
    }

    @Override
    public Record findByApplicantId(Integer applicantId) {
        Applicant applicant = applicantLoadPort.loadApplicantById(applicantId);
        return recordLoadPort.findByApplicant(applicant);
    }
}
