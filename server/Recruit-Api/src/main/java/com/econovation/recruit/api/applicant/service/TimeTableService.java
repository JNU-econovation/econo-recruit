package com.econovation.recruit.api.applicant.service;

import static com.econovation.recruitcommon.consts.RecruitStatic.TIMETABLE_APPLICANT_FIELD;

import com.econovation.recruit.api.applicant.usecase.TimeTableLoadUseCase;
import com.econovation.recruit.api.applicant.usecase.TimeTableRegisterUseCase;
import com.econovation.recruitdomain.domains.applicant.dto.TimeTableDto;
import com.econovation.recruitdomain.domains.timetable.domain.TimeTable;
import com.econovation.recruitdomain.out.TimeTableLoadPort;
import com.econovation.recruitdomain.out.TimeTableRecordPort;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TimeTableService implements TimeTableRegisterUseCase, TimeTableLoadUseCase {
    private final TimeTableLoadPort timeTableLoadPort;
    private final TimeTableRecordPort timeTableRecordPort;
    private final AnswerService answerService;

    @Override
    public List<TimeTableDto> findAll() {
        return null;
    }

    @Override
    public List<TimeTable> getTimeTableByApplicantId(UUID applicantId) {
        return timeTableLoadPort.getTimeTableByApplicantId(applicantId);
    }

    @Override
    @Transactional(readOnly = true)
    public Map<Integer, Map<String, String>> findAllSimpleApplicantWithTimeTable() {
        List<TimeTable> timeTables = timeTableLoadPort.findAll();
        Map<UUID, Map<String, String>> allApplicantVo =
                answerService.findAllApplicantVo(TIMETABLE_APPLICANT_FIELD);
        return timeTables.stream()
                .collect(
                        Collectors.toMap(
                                TimeTable::getStartTime,
                                timeTable -> {
                                    return allApplicantVo.get(timeTable.getApplicantId());
                                }));
    }

    /*    private List<TimeTableInsertDto> toList(HashMap<String, Object> param) {
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
                    new TimeTableInsertDto(startTimes.get(i)));
        }
        return chunkTimeTable;
    }*/

    @Override
    public void execute(UUID applicantId, List<Integer> startTimes) {
        List<TimeTable> timeTableList = new LinkedList<>();
        for (Integer startTime : startTimes) {
            timeTableList.add(
                    TimeTable.builder().startTime(startTime).applicantId(applicantId).build());
        }
        timeTableRecordPort.saveAll(timeTableList);
    }
}
