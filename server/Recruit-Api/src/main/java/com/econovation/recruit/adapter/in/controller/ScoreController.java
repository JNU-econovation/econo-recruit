package com.econovation.recruit.adapter.in.controller;


import com.econovation.recruit.application.port.in.RecordUseCase;
import com.econovation.recruit.application.port.in.ScoreUseCase;
import com.econovation.recruit.application.utils.EntityMapper;
import com.econovation.recruitdomain.domain.dto.CreateScoreDto;
import com.econovation.recruitdomain.domain.dto.UpdateScoreDto;
import com.econovation.recruitdomain.domain.record.Record;
import com.econovation.recruitdomain.domain.score.Score;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ScoreController {
    private final ScoreUseCase scoreUseCase;
    private final RecordUseCase recordUseCase;
    private final EntityMapper entityMapper;

    @PostMapping("/scores")
    public ResponseEntity<Score> createScore(CreateScoreDto createScoreDto) {
        Score score = entityMapper.toScore(createScoreDto);
        score = scoreUseCase.createScore(score);
        return new ResponseEntity<>(score, HttpStatus.OK);
    }

    @PostMapping("/scores/update")
    public ResponseEntity<Score> updateScore(UpdateScoreDto updateScoreDto) {
        Score score = entityMapper.toScore(updateScoreDto);
        // update를 save와 동일
        score = scoreUseCase.createScore(score);
        return new ResponseEntity<>(score, HttpStatus.OK);
    }

    @GetMapping("/scores")
    public ResponseEntity<List<Score>> getScoresByApplicantId(Integer applicantId) {
        List<Score> scores = scoreUseCase.getByApplicantId(applicantId);
        return new ResponseEntity<>(scores, HttpStatus.OK);
    }

    @GetMapping("/scores/records")
    public ResponseEntity<Map<String, Object>> getScoresAndRecordsByApplicantId(
            Integer applicantId) {
        List<Score> scores = scoreUseCase.getByApplicantId(applicantId);
        Record record = recordUseCase.findByApplicantId(applicantId);
        Map<String, Object> map = new HashMap<>();
        map.put("scores", scores);
        map.put("record", record);
        return new ResponseEntity(map, HttpStatus.OK);
    }
}
