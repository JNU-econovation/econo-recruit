package com.econovation.recruit.api.record.service;

import com.econovation.recruit.api.applicant.usecase.AnswerLoadUseCase;
import com.econovation.recruit.api.record.usecase.RecordUseCase;
import com.econovation.recruitdomain.domains.applicant.exception.ApplicantNotFoundException;
import com.econovation.recruitdomain.domains.dto.CreateRecordDto;
import com.econovation.recruitdomain.domains.record.domain.Record;
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

    private final AnswerLoadUseCase answerLoadUseCase;

    @Override
    public Record createRecord(CreateRecordDto recordDto) {
        if (answerLoadUseCase.execute(recordDto.getApplicantId()).isEmpty()) {
            throw ApplicantNotFoundException.EXCEPTION;
        }
        Record record = CreateRecordDto.toRecord(recordDto);
        return recordRecordPort.save(record);
    }

    @Override
    public List<Record> findAll() {
        return recordLoadPort.findAll();
    }

    @Override
    public Record findByApplicantId(String applicantId) {
        return recordLoadPort.findByApplicantId(applicantId);
    }

    @Override
    public void updateRecordUrl(String applicantId, String url) {
        Record record = recordLoadPort.findByApplicantId(applicantId);
        record.updateUrl(url);
    }

    @Override
    public void updateRecordContents(String applicantId, String contents) {
        Record record = recordLoadPort.findByApplicantId(applicantId);
        record.updateRecord(contents);
    }
}
