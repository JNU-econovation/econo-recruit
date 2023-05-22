package com.econovation.recruit.application.port.out;

import com.econovation.recruit.domain.record.Record;

public interface RecordRecordPort {
    Record save(Record record);
}
