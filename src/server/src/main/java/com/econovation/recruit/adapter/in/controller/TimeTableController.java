package com.econovation.recruit.adapter.in.controller;

import com.econovation.recruit.application.port.in.TimeTableUseCase;
import com.econovation.recruit.domain.dto.TimeTableInsertDto;
import com.econovation.recruit.domain.timetable.TimeTable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class TimeTableController {
    private final TimeTableUseCase timeTableUseCase;
    @PostMapping("/timetable")
    public ResponseEntity<List<TimeTable> > createTimeTable(@RequestBody HashMap<String, Object> param,Integer applicantId) {
        List<TimeTableInsertDto> timeTables = timeTableUseCase.submitTimeTable(param,applicantId);

    }
}
