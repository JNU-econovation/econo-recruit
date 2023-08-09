package com.econovation.recruitdomain.persistence;

import com.econovation.recruitdomain.domains.timetable.TimeTable;
import com.econovation.recruitdomain.domains.timetable.TimeTableRepository;
import com.econovation.recruitdomain.out.TimeTableLoadPort;
import com.econovation.recruitdomain.out.TimeTableRecordPort;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class TimeTablePersistenceAdapter implements TimeTableRecordPort, TimeTableLoadPort {
    private static final String NO_OBJECT_EXCEPTION = "해당하는 위치";
    private final JdbcTemplate jdbcTemplate;
    private final TimeTableRepository timeTableRepository;

    //    @Override
    //    @Transactional
    //    public List<TimeTableInsertDto> saveAll(List<TimeTableInsertDto> timeTables, Integer
    // applicantId){
    //        String sql = "INSERT INTO timetable (start_time, end_time, day, applicant_id) " +
    //                "VALUES (?, ?, ?, ?)";
    //        if (!timeTables.isEmpty()) {
    //            batchInsert(timeTables, sql,applicantId);
    //            return timeTables;
    //        }
    //        throw new IllegalArgumentException(NO_OBJECT_EXCEPTION);
    //    }
    //    private void batchInsert(List<TimeTableInsertDto> timeTables, String sql, Integer
    // applicantId) {
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
