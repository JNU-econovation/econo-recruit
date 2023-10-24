package com.econovation.recruitdomain.domains.timetable.adaptor;

import com.econovation.recruitcommon.annotation.Adaptor;
import com.econovation.recruitdomain.domains.timetable.domain.TimeTable;
import com.econovation.recruitdomain.domains.timetable.domain.TimeTableRepository;
import com.econovation.recruitdomain.domains.timetable.exception.TimeTableNotFoundException;
import com.econovation.recruitdomain.out.TimeTableLoadPort;
import com.econovation.recruitdomain.out.TimeTableRecordPort;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Adaptor
@RequiredArgsConstructor
public class TimeTableAdapter implements TimeTableRecordPort, TimeTableLoadPort {
    private final TimeTableRepository timeTableRepository;

    @Override
    public List<TimeTable> saveAll(List<TimeTable> timeTables) {
        return timeTableRepository.saveAll(timeTables);
    }

    @Override
    public List<TimeTable> getTimeTableByApplicantId(String applicantId) {
        List<TimeTable> timeTables = timeTableRepository.findByApplicantId(applicantId);
        if (timeTables.isEmpty()) {
            throw TimeTableNotFoundException.EXCEPTION;
        }
        return timeTables;
    }

    @Override
    public List<TimeTable> findAll() {
        List<TimeTable> timeTables = timeTableRepository.findAll();
        if (timeTables.isEmpty()) {
            throw TimeTableNotFoundException.EXCEPTION;
        }
        return timeTables;
    }
}
