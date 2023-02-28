package com.econovation.recruit.adapter.out.persistence;

import com.econovation.recruit.application.port.out.TimeTableLoadPort;
import com.econovation.recruit.application.port.out.TimeTableRecordPort;
import com.econovation.recruit.domain.dto.ResumeInsertDto;
import com.econovation.recruit.domain.dto.TimeTableInsertDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.sql.PreparedStatement;
import java.util.List;
@Component
@RequiredArgsConstructor
public class TimeTablePersistenceAdapter implements TimeTableRecordPort, TimeTableLoadPort {
    private static final String NO_OBJECT_EXCEPTION = "해당하는 위치";
    private final JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public List<TimeTableInsertDto> saveAll(List<TimeTableInsertDto> timeTables, Integer applicantId){
        String sql = "INSERT INTO timetable (start_time, end_time, day, applicant_id) " +
                "VALUES (?, ?, ?, ?)";
        if (!timeTables.isEmpty()) {
            batchInsert(timeTables, sql,applicantId);
            return timeTables;
        }
        throw new IllegalArgumentException(NO_OBJECT_EXCEPTION);
    }
    private void batchInsert(List<TimeTableInsertDto> timeTables, String sql, Integer applicantId) {
        jdbcTemplate.batchUpdate(sql,
                timeTables,
                timeTables.size(),
                (PreparedStatement ps, TimeTableInsertDto timeTable) -> {
                    ps.setString(1, timeTable.getStartTime());
                    ps.setString(2,timeTable.getEndTime());
                    ps.setString(3, timeTable.getDay());
                    ps.setInt(4, applicantId);
                });
    }
}
