package com.econovation.recruit.application.service;

import com.econovation.recruit.application.port.in.TimeTableUseCase;
import com.econovation.recruitdomain.domains.applicant.Applicant;
import com.econovation.recruitdomain.domains.dto.TimeTableInsertDto;
import com.econovation.recruitdomain.domains.timetable.TimeTable;
import com.econovation.recruitdomain.out.ApplicantLoadPort;
import com.econovation.recruitdomain.out.TimeTableLoadPort;
import com.econovation.recruitdomain.out.TimeTableRecordPort;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TimeTableService implements TimeTableUseCase {
    private final ApplicantLoadPort applicantLoadPort;
    private final TimeTableLoadPort timeTableLoadPort;
    private final TimeTableRecordPort timeTableRecordPort;

    //    @Override
    //    public List<TimeTableInsertDto> submitTimeTable(HashMap<String, Object> param,Integer
    // applicantId) {
    //        List<TimeTableInsertDto> timeTableInsertDtos = toList(param);
    //        return timeTableRecordPort.saveAll();
    //    }

    @Override
    public List<TimeTable> submitTimeTable(
            List<TimeTableInsertDto> timeTable, Integer applicantId) {
        List<TimeTable> timeTables = timeTable.toTimeTables(timetable, applicantId);
        return timeTableRecordPort.saveAll(timeTables);
    }

    @Override
    public List<TimeTable> getTimeTableByApplicantId(Integer applicantId) {
        Applicant applicant = applicantLoadPort.loadApplicantById(applicantId);
        return timeTableLoadPort.getTimeTableByApplicantId(applicant);
    }

    @Override
    public List<TimeTable> findAll() {
        return timeTableLoadPort.findAll();
    }

    private List<TimeTableInsertDto> toList(HashMap<String, Object> param) {
        List<TimeTableInsertDto> chunkTimeTable = new LinkedList<>();
        Gson gson = new Gson();
        // JsonParser Deprecated -> JsonParser static import 변경
        JsonElement startTime = JsonParser.parseString(param.get("startTime").toString());
        JsonElement endTime = JsonParser.parseString(param.get("endTime").toString());
        JsonElement day = JsonParser.parseString(param.get("day").toString());
        // JsonElement -> List<String>으로 파싱

        List<String> startTimes =
                gson.fromJson(startTime, (new TypeToken<List<String>>() {}).getType());
        List<String> endTimes =
                gson.fromJson(endTime, (new TypeToken<List<String>>() {}).getType());
        List<String> days = gson.fromJson(day, (new TypeToken<List<String>>() {}).getType());

        for (int i = 0; i < startTimes.size(); i++) {
            chunkTimeTable.add(
                    new TimeTableInsertDto(startTimes.get(i), endTimes.get(i), days.get(i)));
        }
        return chunkTimeTable;
    }
}
