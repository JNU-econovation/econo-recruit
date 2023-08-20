package com.econovation.recruit.api.score.controller;

import static com.econovation.recruitcommon.consts.RecruitStatic.SCORE_SUCCESS_REGISTER_MESSAGE;

import com.econovation.recruit.application.port.in.RecordUseCase;
import com.econovation.recruit.application.port.in.ScoreUseCase;
import com.econovation.recruit.application.utils.EntityMapper;
import com.econovation.recruitdomain.domains.dto.CreateScoreDto;
import com.econovation.recruitdomain.domains.record.Record;
import com.econovation.recruitdomain.domains.score.Score;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "[5.0] Score 평가 API", description = "Score 평가 API")
@RequiredArgsConstructor
public class ScoreController {
    private final ScoreUseCase scoreUseCase;
    private final RecordUseCase recordUseCase;
    private final EntityMapper entityMapper;

    @Operation(description = "Score 평가 생성")
    @PostMapping("/scores")
    public ResponseEntity<String> createScore(CreateScoreDto createScoreDto) {
        scoreUseCase.createScore(createScoreDto);
        return new ResponseEntity<>(SCORE_SUCCESS_REGISTER_MESSAGE, HttpStatus.OK);
    }

    //    @Operation(description = "Score 평가 수정")
    //    @PutMapping("/scores")
    //    public ResponseEntity<Score> updateScore(UpdateScoreDto updateScoreDto) {
    ////        score = scoreUseCase.createScore(score);
    //        return new ResponseEntity<>(score, HttpStatus.OK);
    //    }

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
