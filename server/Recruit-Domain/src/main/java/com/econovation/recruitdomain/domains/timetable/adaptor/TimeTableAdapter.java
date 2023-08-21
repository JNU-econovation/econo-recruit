package com.econovation.recruitdomain.domains.timetable.adaptor;

import com.econovation.recruitcommon.annotation.Adaptor;
import com.econovation.recruitdomain.domains.timetable.domain.TimeTable;
import com.econovation.recruitdomain.domains.timetable.domain.TimeTableRepository;
import com.econovation.recruitdomain.out.TimeTableLoadPort;
import com.econovation.recruitdomain.out.TimeTableRecordPort;
import java.util.List;
import java.util.UUID;
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
    public List<TimeTable> getTimeTableByApplicantId(UUID applicantId) {
        return timeTableRepository.findByApplicantId(applicantId);
    }

    @Override
    public List<TimeTable> findAll() {
        return timeTableRepository.findAll();
    }
}
