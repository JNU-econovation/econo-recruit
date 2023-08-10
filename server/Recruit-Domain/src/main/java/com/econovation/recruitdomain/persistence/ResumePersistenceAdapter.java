package com.econovation.recruitdomain.persistence;

import com.econovation.recruitdomain.domains.applicant.Applicant;
import com.econovation.recruitdomain.domains.dto.ResumeInsertDto;
import com.econovation.recruitdomain.domains.resume.Resume;
import com.econovation.recruitdomain.domains.resume.ResumeRepository;
import com.econovation.recruitdomain.out.ResumeLoadPort;
import com.econovation.recruitdomain.out.ResumeRecordPort;
import java.sql.PreparedStatement;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ResumePersistenceAdapter implements ResumeRecordPort, ResumeLoadPort {
    private static final String NO_OBJECT_EXCEPTION = "데이터가 존재하지 않습니다.";
    private static final String NO_RESUME_EXCEPTION = "데이터가 존재하지 않습니다.";
    private final JdbcTemplate jdbcTemplate;
    private final ResumeRepository resumeRepository;
    //    @Override
    //    @Transactional
    //    public List<ResumeInsertDto> saveAll(List<ResumeInsertDto> resumes){
    //        String sql = "INSERT INTO resume (applicant_id, question_id, answer) " +
    //                "VALUES (?, ?, ?)";
    //        if (!resumes.isEmpty()) {
    //            batchInsert(resumes, sql);
    //            return resumes;
    //        }
    //        throw new IllegalArgumentException(NO_OBJECT_EXCEPTION);
    //    }

    @Override
    public List<Resume> saveAll(List<Resume> resumes) {
        return resumeRepository.saveAll(resumes);
    }

    private void batchInsert(List<ResumeInsertDto> resumes, String sql) {
        jdbcTemplate.batchUpdate(
                sql,
                resumes,
                resumes.size(),
                (PreparedStatement ps, ResumeInsertDto resume) -> {
                    ps.setInt(1, resume.getApplicantId());
                    ps.setInt(2, resume.getQuestionId());
                    ps.setString(3, resume.getAnswer());
                });
    }

    @Override
    public List<Resume> findAll() {
        List<Resume> all = resumeRepository.findAll();
        if (all.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return all;
    }

    @Override
    public Resume findById(Integer resumeId) {
        return resumeRepository
                .findById(resumeId)
                .orElseThrow(() -> new IllegalArgumentException(NO_RESUME_EXCEPTION));
    }

    @Override
    public List<Resume> findByApplicant(Applicant applicant) {
        return resumeRepository.findByApplicant(applicant);
    }
}
