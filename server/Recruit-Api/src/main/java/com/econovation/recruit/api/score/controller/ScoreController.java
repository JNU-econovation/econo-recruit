package com.econovation.recruit.api.score.controller;

import static com.econovation.recruitcommon.consts.RecruitStatic.SCORE_SUCCESS_REGISTER_MESSAGE;
import static com.econovation.recruitcommon.consts.RecruitStatic.SCORE_SUCCESS_UPDATE_MESSAGE;

import com.econovation.recruit.api.score.docs.ScoreExceptionDocs;
import com.econovation.recruit.api.score.usecase.ScoreUseCase;
import com.econovation.recruitcommon.annotation.ApiErrorExceptionsExample;
import com.econovation.recruitdomain.domains.dto.CreateScoreDto;
import com.econovation.recruitdomain.domains.dto.ScoreAverageDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "[5.0] Score 평가 API", description = "Score 평가 API")
@RequiredArgsConstructor
public class ScoreController {
    private final ScoreUseCase scoreUseCase;

    @Operation(description = "Score 평가 생성")
    @ApiErrorExceptionsExample(ScoreExceptionDocs.class)
    @PostMapping("/scores")
    public ResponseEntity<String> createScore(CreateScoreDto createScoreDto) {
        scoreUseCase.createScore(createScoreDto);
        return new ResponseEntity<>(SCORE_SUCCESS_REGISTER_MESSAGE, HttpStatus.OK);
    }

    @Operation(description = "Score 평가 수정")
    @ApiErrorExceptionsExample(ScoreExceptionDocs.class)
    @PutMapping("/scores")
    public ResponseEntity<String> updateScore(CreateScoreDto updateScoreDto) {
        scoreUseCase.updateScore(updateScoreDto);
        return new ResponseEntity<>(SCORE_SUCCESS_UPDATE_MESSAGE, HttpStatus.OK);
    }

    @Operation(description = "Score 평가 조회")
    @ApiErrorExceptionsExample(ScoreExceptionDocs.class)
    @GetMapping("/scores")
    public ResponseEntity<ScoreAverageDto> getScoresByApplicantId(UUID applicantId) {
        ScoreAverageDto scores = scoreUseCase.getApplicantScoreWithAverage(applicantId);
        return new ResponseEntity<>(scores, HttpStatus.OK);
    }

    // 삭제 기능은 없습니다.
}
