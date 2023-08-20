package com.econovation.recruit.application.service;

import com.econovation.recruit.application.port.in.ScoreUseCase;
import com.econovation.recruit.config.security.SecurityUtils;
import com.econovation.recruitdomain.domains.dto.CreateScoreDto;
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
    public void createScore(CreateScoreDto scoreDto) {
        Long idpId = SecurityUtils.getCurrentUserId();
        List<Score> scores =
                scoreDto.getScoreVo().stream()
                        .map(
                                scoreVo ->
                                        Score.builder()
                                                .applicantId(scoreDto.getApplicantId())
                                                .score(scoreVo.getScore())
                                                .criteria(scoreVo.getCreteria())
                                                .idpId(idpId)
                                                .build())
                        .collect(java.util.stream.Collectors.toList());
        scoreRecordPort.save(scores);
    }

    @Override
    public List<Score> getByApplicantId(Integer applicantId) {
        return scoreLoadPort.findByApplicantId(applicantId);
    }
}
