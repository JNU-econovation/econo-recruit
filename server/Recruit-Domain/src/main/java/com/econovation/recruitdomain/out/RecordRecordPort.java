package com.econovation.recruitdomain.out;

import com.econovation.recruitdomain.domains.record.domain.Record;

public interface RecordRecordPort {
    Record save(Record record);
}
