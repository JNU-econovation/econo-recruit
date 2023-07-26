package com.econovation.recruitdomain.persistence;


import com.econovation.recruitdomain.domain.applicant.Applicant;
import com.econovation.recruitdomain.domain.record.Record;
import com.econovation.recruitdomain.domain.record.RecordRepository;
import com.econovation.recruitdomain.out.RecordLoadPort;
import com.econovation.recruitdomain.out.RecordRecordPort;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
        return recordRepository
                .findByApplicant(applicant)
                .orElseThrow(() -> new IllegalArgumentException(NO_MATCH_RECORD));
    }
}
