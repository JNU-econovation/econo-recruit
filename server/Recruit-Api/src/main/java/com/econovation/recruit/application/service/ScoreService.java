package com.econovation.recruit.application.service;

import com.econovation.recruit.application.port.in.ScoreUseCase;
import com.econovation.recruitdomain.domains.score.Score;
import com.econovation.recruitdomain.out.ScoreLoadPort;
import com.econovation.recruitdomain.out.ScoreRecordPort;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScoreService implements ScoreUseCase {
    private final ScoreRecordPort scoreRecordPort;
    private final ScoreLoadPort scoreLoadPort;

    @Override
    public Score createScore(Score score) {
        return scoreRecordPort.save(score);
    }

    @Override
    public List<Score> getByApplicantId(Integer applicantId) {
        return scoreLoadPort.findByApplicantId(applicantId);
    }
}
