package com.econovation.recruit.api.score.service;

import com.econovation.recruit.api.config.security.SecurityUtils;
import com.econovation.recruit.api.score.usecase.ScoreUseCase;
import com.econovation.recruitdomain.domains.dto.CreateScoreDto;
import com.econovation.recruitdomain.domains.dto.ScoreAverageDto;
import com.econovation.recruitdomain.domains.interviewer.domain.Interviewer;
import com.econovation.recruitdomain.domains.score.domain.Score;
import com.econovation.recruitdomain.out.InterviewerLoadPort;
import com.econovation.recruitdomain.out.ScoreLoadPort;
import com.econovation.recruitdomain.out.ScoreRecordPort;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ScoreService implements ScoreUseCase {
    private final ScoreRecordPort scoreRecordPort;
    private final ScoreLoadPort scoreLoadPort;
    private final InterviewerLoadPort interviewerLoadPort;

    @Override
    public void createScore(CreateScoreDto scoreDto) {
        Long idpId = SecurityUtils.getCurrentUserId();
        AtomicInteger index = new AtomicInteger(1);
        List<Score> scores =
                scoreDto.getScoreVo().stream()
                        .map(
                                scoreVo ->
                                        Score.builder()
                                                .applicantId(scoreDto.getApplicantId())
                                                .criteria(index.getAndIncrement())
                                                .score(scoreVo)
                                                .idpId(idpId)
                                                .build())
                        .toList();
        scoreRecordPort.save(scores);
    }

    @Override
    @Transactional
    public void updateScore(CreateScoreDto scoreDto) {
        Long idpId = SecurityUtils.getCurrentUserId();
        scoreLoadPort.findByApplicantId(scoreDto.getApplicantId()).stream()
                .filter(score -> score.getIdpId().equals(idpId))
                .forEach(
                        score -> {
                            score.updateScore(scoreDto.getScoreVo().get(score.getCriteria() - 1));
                        });
    }

    @Override
    public Map<String, List<Float>> getByApplicantId(
            String applicantId, List<Score> scores, Map<Long, String> interviewers) {
        // idpId, name
        Map<String, List<Float>> result =
                scores.stream()
                        .collect(
                                Collectors.groupingBy(
                                        score -> interviewers.get(score.getIdpId()),
                                        Collectors.mapping(Score::getScore, Collectors.toList())));
        List<Float> averageScores = calculateAverageScore(result);
        result.put("average", averageScores);

        return result;
    }

    @Override
    public ScoreAverageDto getApplicantScoreWithAverage(String applicantId) {
        Long userId = SecurityUtils.getCurrentUserId();

        List<Score> scores = scoreLoadPort.findByApplicantId(applicantId);
        Map<Long, String> interviewers = getAssociatedMapWithIdpIdWithName(scores);

        Map<String, List<Float>> byApplicantId =
                getByApplicantId(applicantId, scores, interviewers);

        List<Float> average = byApplicantId.get("average");
        Float totalAverage = average.stream().reduce(0f, Float::sum) / average.size();
        List<Float> myScore = byApplicantId.get(interviewers.get(userId));
        byApplicantId.remove(interviewers.get(userId));
        byApplicantId.remove("average");
        return ScoreAverageDto.of(totalAverage, average, myScore, byApplicantId);
    }

    private List<Float> calculateAverageScore(Map<String, List<Float>> result) {
        List<Float> averageScores = new LinkedList<>();

        // 리스트의 각 위치에 대해 값을 더하고 카운트
        result.forEach(
                (key, value) -> {
                    for (int i = 0; i < value.size(); i++) {
                        if (averageScores.size() <= i) {
                            averageScores.add(value.get(i)); // 새로운 값 추가
                        } else {
                            averageScores.set(i, averageScores.get(i) + value.get(i)); // 값 더하기
                        }
                    }
                });

        // 리스트의 각 값을 리스트의 크기로 나눠서 평균 계산
        int size = result.size();
        for (int i = 0; i < averageScores.size(); i++) {
            averageScores.set(i, averageScores.get(i) / size);
        }

        return averageScores;
    }

    @NotNull
    private Map<Long, String> getAssociatedMapWithIdpIdWithName(List<Score> scores) {
        return interviewerLoadPort
                .loadInterviewerByIdpIds(scores.stream().map(Score::getIdpId).toList())
                .stream()
                .collect(Collectors.toMap(Interviewer::getId, Interviewer::getName));
    }
}
