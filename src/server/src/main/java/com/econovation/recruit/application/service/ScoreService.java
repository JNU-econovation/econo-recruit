package com.econovation.recruit.application.service;

import com.econovation.recruit.application.port.in.ScoreUseCase;
import com.econovation.recruit.application.port.out.ApplicantLoadPort;
import com.econovation.recruit.application.port.out.ScoreLoadPort;
import com.econovation.recruit.application.port.out.ScoreRecordPort;
import com.econovation.recruit.domain.applicant.Applicant;
import com.econovation.recruit.domain.score.Score;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScoreService implements ScoreUseCase {
    private final ScoreRecordPort scoreRecordPort;
    private final ScoreLoadPort scoreLoadPort;
    private final ApplicantLoadPort applicantLoadPort;

    @Override
    public Score createScore(Score score) {
        return scoreRecordPort.save(score);
    }

    @Override
    public List<Score> getByApplicantId(Integer applicantId) {
        Applicant applicant = applicantLoadPort.loadApplicantById(applicantId);
        return scoreLoadPort.findByApplicant(applicant);
    }
}
