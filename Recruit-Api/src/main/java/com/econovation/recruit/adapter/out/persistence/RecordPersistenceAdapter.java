package com.econovation.recruit.adapter.out.persistence;

import com.econovation.recruit.application.port.out.RecordLoadPort;
import com.econovation.recruit.application.port.out.RecordRecordPort;
import com.econovation.recruit.domain.applicant.Applicant;
import com.econovation.recruit.domain.record.Record;
import com.econovation.recruit.domain.record.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RecordPersistenceAdapter implements RecordLoadPort, RecordRecordPort {
    private static final String NO_MATCH_RECORD = "해당하는 RECORD가 존재하지 않습니다";
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
    public Record findByApplicant(Applicant applicant) {
        return recordRepository.findByApplicant(applicant).orElseThrow(() -> new IllegalArgumentException(NO_MATCH_RECORD));
    }
}
