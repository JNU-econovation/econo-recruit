package com.econovation.recruitdomain.out;


import com.econovation.recruitdomain.domain.record.Record;

public interface RecordRecordPort {
    Record save(Record record);
}
