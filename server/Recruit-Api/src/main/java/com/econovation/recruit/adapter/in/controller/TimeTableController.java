package com.econovation.recruit.adapter.in.controller;

import com.econovation.recruit.application.port.in.TimeTableUseCase;
import com.econovation.recruitdomain.domains.dto.TimeTableInsertDto;
import com.econovation.recruitdomain.domains.timetable.TimeTable;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class TimeTableController {
    private final TimeTableUseCase timeTableUseCase;

    //    @PostMapping("/timetable")
    //    public ResponseEntity<List<TimeTable>> createTimeTable(@RequestBody HashMap<String,
    // Object> param, Integer applicantId) {
    //        List<TimeTableInsertDto> timeTables = timeTableUseCase.submitTimeTable(param,
    // applicantId);
    //        return new ResponseEntity(timeTables, HttpStatus.OK);
    //    }

    @PostMapping("/timetable")
    public ResponseEntity<List<TimeTable>> createTimeTable(
            @RequestBody List<TimeTableInsertDto> timetable, Integer applicantId) {
        List<TimeTable> timeTables = timeTableUseCase.submitTimeTable(timetable, applicantId);
        return new ResponseEntity(timeTables, HttpStatus.OK);
        // Do something with the timetable list
    }

    @GetMapping("/timetable")
    public ResponseEntity<List<TimeTable>> getTimeTables() {
        return new ResponseEntity(timeTableUseCase.findAll(), HttpStatus.OK);
    }

    @GetMapping("/timetable/{applicantId}")
    public ResponseEntity<List<TimeTable>> getTimeTables(
            @PathVariable(name = "applicantId") Integer applicantId) {
        List<TimeTable> timeTables = timeTableUseCase.getTimeTableByApplicantId(applicantId);
        return new ResponseEntity(timeTables, HttpStatus.OK);
    }
}
