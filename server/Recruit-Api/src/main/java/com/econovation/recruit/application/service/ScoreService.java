package com.econovation.recruit.application.service;

import static com.econovation.recruitcommon.consts.RecruitStatic.CRETERIA;
import static com.econovation.recruitcommon.consts.RecruitStatic.CRETERIA_SET;

import com.econovation.recruit.application.port.in.ScoreUseCase;
import com.econovation.recruit.config.security.SecurityUtils;
import com.econovation.recruitdomain.domains.dto.CreateScoreDto;
import com.econovation.recruitdomain.domains.dto.ScoreVo;
import com.econovation.recruitdomain.domains.score.Score;
import com.econovation.recruitdomain.out.ScoreLoadPort;
import com.econovation.recruitdomain.out.ScoreRecordPort;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.PatternMatchUtils;

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
    @Transactional
    public void updateScore(CreateScoreDto scoreDto) {
        Long idpId = SecurityUtils.getCurrentUserId();
        Map<String, Float> scoreVoMap = scoreDto.getScoreVo().stream()
                .collect(Collectors.toMap(ScoreVo::getCreteria, ScoreVo::getScore));

        scoreLoadPort.findByApplicantId(scoreDto.getApplicantId()).stream()
                .filter(score ->
                        CRETERIA_SET.contains(score.getCriteria()) && score.getIdpId().equals(idpId)
                )
                .forEach(score -> {
                    Float matchingScore = scoreVoMap.get(score.getCriteria());
                    if (matchingScore != null) {
                        score.updateScore(matchingScore);
                    }
                });
    }

    @Override
    public List<Score> getByApplicantId(UUID applicantId) {
        return scoreLoadPort.findByApplicantId(applicantId);
    }
}
