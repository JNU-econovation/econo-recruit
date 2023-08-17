package com.econovation.recruit.application.service;

import com.econovation.recruit.application.port.in.RecordUseCase;
import com.econovation.recruitdomain.domains.record.Record;
import com.econovation.recruitdomain.out.RecordLoadPort;
import com.econovation.recruitdomain.out.RecordRecordPort;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecordService implements RecordUseCase {
    private final RecordRecordPort recordRecordPort;
    private final RecordLoadPort recordLoadPort;

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
        return recordLoadPort.findByApplicantId(applicantId);
    }
}
