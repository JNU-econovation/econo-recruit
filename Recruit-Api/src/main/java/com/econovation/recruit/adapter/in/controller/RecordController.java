package com.econovation.recruit.adapter.in.controller;

import com.econovation.recruit.application.port.in.RecordUseCase;
import com.econovation.recruit.application.utils.EntityMapper;
import com.econovation.recruit.domain.dto.CreateRecordDto;
import com.econovation.recruit.domain.record.Record;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class RecordController {
    private final RecordUseCase recordUseCase;
    private final EntityMapper entityMapper;

    @PostMapping("/records")
    public ResponseEntity<Record> createRecord(CreateRecordDto createRecordDto) {
        Record record = entityMapper.toRecord(createRecordDto);
        return new ResponseEntity(recordUseCase.createRecord(record), HttpStatus.OK);
    }

//    @GetMapping("/records")
//    public ResponseEntity<List<Record>> findAll() {
//        List<Record> records = recordUseCase.findAll();
//    }
    @GetMapping("/records")
    public ResponseEntity<Record> findByApplicantId(Integer applicantId) {
        Record record = recordUseCase.findByApplicantId(applicantId);
        return new ResponseEntity(record, HttpStatus.OK);
    }
}
