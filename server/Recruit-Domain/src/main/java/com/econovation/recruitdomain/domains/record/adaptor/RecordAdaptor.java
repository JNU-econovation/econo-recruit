package com.econovation.recruitdomain.domains.record.adaptor;

import com.econovation.recruitcommon.annotation.Adaptor;
import com.econovation.recruitdomain.domains.record.domain.Record;
import com.econovation.recruitdomain.domains.record.domain.RecordRepository;
import com.econovation.recruitdomain.domains.record.exception.RecordNotFoundException;
import com.econovation.recruitdomain.out.RecordLoadPort;
import com.econovation.recruitdomain.out.RecordRecordPort;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@Adaptor
@RequiredArgsConstructor
public class RecordAdaptor implements RecordLoadPort, RecordRecordPort {
    private final RecordRepository recordRepository;

    @Override
    public Record save(Record record) {
        return recordRepository.save(record);
    }

    @Override
    public List<Record> findAll() {
        return recordRepository.findAll();
    }

    @Override
    public Record findByApplicantId(UUID applicantId) {
        return recordRepository
                .findByApplicantId(applicantId)
                .orElseThrow(() -> RecordNotFoundException.EXCEPTION);
    }
}
