package com.econovation.recruitdomain.out;

import com.econovation.recruitdomain.domains.record.Record;
import java.util.List;

public interface RecordLoadPort {
    List<Record> findAll();

    Record findByApplicantId(Integer applicantId);
}
