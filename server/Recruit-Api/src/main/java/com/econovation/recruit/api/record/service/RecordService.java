package com.econovation.recruit.api.record.service;

import com.econovation.recruit.api.applicant.usecase.ApplicantQueryUseCase;
import com.econovation.recruit.api.record.usecase.RecordUseCase;
import com.econovation.recruitdomain.domains.applicant.exception.ApplicantNotFoundException;
import com.econovation.recruitdomain.domains.dto.CreateRecordDto;
import com.econovation.recruitdomain.domains.dto.UpdateRecordDto;
import com.econovation.recruitdomain.domains.record.domain.Record;
import com.econovation.recruitdomain.domains.record.exception.RecordDuplicateCreatedException;
import com.econovation.recruitdomain.out.RecordLoadPort;
import com.econovation.recruitdomain.out.RecordRecordPort;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RecordService implements RecordUseCase {
    private final RecordRecordPort recordRecordPort;
    private final RecordLoadPort recordLoadPort;

    private final ApplicantQueryUseCase answerQueryUseCase;

    @Override
    @Transactional
    public Record createRecord(CreateRecordDto recordDto) {
        if (answerQueryUseCase.execute(recordDto.getApplicantId()) == null) {
            throw ApplicantNotFoundException.EXCEPTION;
        }
        if (recordLoadPort.findByApplicantId(recordDto.getApplicantId()).isPresent()) {
            throw RecordDuplicateCreatedException.EXCEPTION;
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
        return recordLoadPort.findByApplicantId(applicantId).get();
    }

    @Override
    @Transactional
    public void updateRecordUrl(String applicantId, String url) {
        recordLoadPort
                .findByApplicantId(applicantId)
                .ifPresent(
                        record -> {
                            record.updateUrl(url);
                        });
    }

    @Override
    @Transactional
    public void updateRecordContents(String applicantId, String contents) {
        recordLoadPort
                .findByApplicantId(applicantId)
                .ifPresent(
                        record -> {
                            record.updateRecord(contents);
                        });
    }

    @Override
    @Transactional
    public void updateRecord(String applicantId, UpdateRecordDto updateRecordDto) {
        recordLoadPort
                .findByApplicantId(applicantId)
                .ifPresent(
                        record -> {
                            if (updateRecordDto.getUrl() != null) {
                                record.updateUrl(updateRecordDto.getUrl());
                            }
                            if (updateRecordDto.getRecord() != null) {
                                record.updateRecord(updateRecordDto.getRecord());
                            }
                        });
    }
}
