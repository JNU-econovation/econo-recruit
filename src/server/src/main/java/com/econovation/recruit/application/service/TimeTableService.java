package com.econovation.recruit.application.service;

import com.econovation.recruit.application.port.in.TimeTableUseCase;
import com.econovation.recruit.application.port.out.TimeTableLoadPort;
import com.econovation.recruit.application.port.out.TimeTableRecordPort;
import com.econovation.recruit.domain.dto.ResumeInsertDto;
import com.econovation.recruit.domain.dto.TimeTableInsertDto;
import com.econovation.recruit.domain.timetable.TimeTable;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TimeTableService implements TimeTableUseCase {
    private final TimeTableLoadPort timeTableLoadPort;
    private final TimeTableRecordPort timeTableRecordPort;
    @Override
    public List<TimeTableInsertDto> submitTimeTable(HashMap<String, Object> param,Integer applicantId) {
        List<TimeTableInsertDto> timeTableInsertDtos = toList(param);
        return timeTableRecordPort.saveAll(timeTableInsertDtos,applicantId);

    }
    private List<TimeTableInsertDto> toList(HashMap<String, Object> param) {
        List<TimeTableInsertDto> chunkTimeTable = new LinkedList<>();
        Gson gson = new Gson();
        // JsonParser Deprecated -> JsonParser static import 변경
        JsonElement startTime = JsonParser.parseString(param.get("startTime").toString());
        JsonElement endTime = JsonParser.parseString(param.get("endTime").toString());
        JsonElement day = JsonParser.parseString(param.get("day").toString());
        // JsonElement -> List<String>으로 파싱
        List<String> startTimes = gson.fromJson(startTime, (new TypeToken<List<String>>() {
        }).getType());
        List<String> endTimes = gson.fromJson(endTime, (new TypeToken<List<String>>() {
        }).getType());
        List<String> days = gson.fromJson(day, (new TypeToken<List<String>>() {
        }).getType());

        for (int i = 0; i < startTimes.size(); i++) {
            chunkTimeTable.add(new TimeTableInsertDto(startTimes.get(i),endTimes.get(i),days.get(i)));
        }
        return chunkTimeTable;
    }
}

