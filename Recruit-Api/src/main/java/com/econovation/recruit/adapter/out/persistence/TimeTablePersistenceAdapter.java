package com.econovation.recruit.adapter.out.persistence;

import com.econovation.recruit.application.port.out.TimeTableLoadPort;
import com.econovation.recruit.application.port.out.TimeTableRecordPort;
import com.econovation.recruit.domain.applicant.Applicant;
import com.econovation.recruit.domain.timetable.TimeTable;
import com.econovation.recruit.domain.timetable.TimeTableRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Slf4j
@RequiredArgsConstructor
public class TimeTablePersistenceAdapter implements TimeTableRecordPort, TimeTableLoadPort {
    private static final String NO_OBJECT_EXCEPTION = "해당하는 위치";
    private final JdbcTemplate jdbcTemplate;
    private final TimeTableRepository timeTableRepository;

//    @Override
//    @Transactional
//    public List<TimeTableInsertDto> saveAll(List<TimeTableInsertDto> timeTables, Integer applicantId){
//        String sql = "INSERT INTO timetable (start_time, end_time, day, applicant_id) " +
//                "VALUES (?, ?, ?, ?)";
//        if (!timeTables.isEmpty()) {
//            batchInsert(timeTables, sql,applicantId);
//            return timeTables;
//        }
//        throw new IllegalArgumentException(NO_OBJECT_EXCEPTION);
//    }
//    private void batchInsert(List<TimeTableInsertDto> timeTables, String sql, Integer applicantId) {
//        jdbcTemplate.batchUpdate(sql,
//                timeTables,
//                timeTables.size(),
//                (PreparedStatement ps, TimeTableInsertDto timeTable) -> {
//                    ps.setString(1, timeTable.getStartTime());
//                    ps.setString(2,timeTable.getEndTime());
//                    ps.setString(3, timeTable.getDay());
//                    ps.setInt(4, applicantId);
//                });
//    }

    @Override
    public List<TimeTable> saveAll(List<TimeTable> timeTables) {
        for (TimeTable t:timeTables) {
            log.info(t.getDay() + " / " + t.getEndTime() + " / " + t.getEndTime() + " / " + t.getStartTime());
        }
        return timeTableRepository.saveAll(timeTables);
    }

    @Override
    public List<TimeTable> getTimeTableByApplicantId(Applicant applicant) {
        List<TimeTable> byApplicant = timeTableRepository.findByApplicant(applicant);
        if(byApplicant.isEmpty()){
            throw new IllegalArgumentException(NO_OBJECT_EXCEPTION);
        }
        return byApplicant;
    }
    @Override
    public List<TimeTable> findAll() {
        return timeTableRepository.findAll();
    }
}
