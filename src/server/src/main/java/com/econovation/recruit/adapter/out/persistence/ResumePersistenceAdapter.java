package com.econovation.recruit.adapter.out.persistence;

import com.econovation.recruit.domain.resume.Resume;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.transaction.Transactional;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ResumePersistenceAdapter {
    private final JdbcTemplate jdbcTemplate;


    @Transactional
    public void saveAll(List<ResumeInsertDto> resumes) {
        String sql = "INSERT INTO resume (applicant_id, question_id, answer) " +
                "VALUES (?, ?, ?)";
        if (!resumes.isEmpty()) {
            batchInsert(resumes, sql);
        }
    }
    private void batchInsert(List<ResumeInsertDto> resumes, String sql) {
        jdbcTemplate.batchUpdate(sql,
                resumes,
                resumes.size(),
                (PreparedStatement ps, ResumeInsertDto resume) -> {
                    ps.setInt(1, resume.getApplicantId());
                    ps.setInt(2,resume.getQuestionId());
                    ps.setString(3, resume.getAnswer());
                });
    }
}
