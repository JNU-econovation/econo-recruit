package com.econovation.recruit.api.example;

import com.econovation.recruitcommon.annotation.ApiErrorCodeExample;
import com.econovation.recruitcommon.annotation.DisableSwaggerSecurity;
import com.econovation.recruitdomain.domains.applicant.exception.ApplicantErrorCode;
import com.econovation.recruitdomain.domains.card.exception.CardErrorCode;
import com.econovation.recruitdomain.domains.comment.exception.CommentErrorCode;
import com.econovation.recruitdomain.domains.interviewer.exception.InterviewerErrorCode;
import com.econovation.recruitdomain.domains.label.exception.LabelErrorCode;
import com.econovation.recruitdomain.domains.record.exception.RecordErrorCode;
import com.econovation.recruitdomain.domains.score.exception.ScoreErrorCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/examples")
@RequiredArgsConstructor
@Tag(name = "xx. [예시] 에러코드 문서화")
public class ExampleController {
    @GetMapping("/health")
    @DisableSwaggerSecurity
    public void health() {}

    @GetMapping("/applicants")
    @Operation(summary = "지원서 등록 관련 에러코드 나열")
    @ApiErrorCodeExample(ApplicantErrorCode.class)
    public void getApplicantErrorCode() {}

    @GetMapping("/card")
    @Operation(summary = "칸반 등록 관련 에러코드 나열")
    @ApiErrorCodeExample(CardErrorCode.class)
    public void getCardErrorCode() {}

    @GetMapping("/comment")
    @Operation(summary = "댓글 등록 관련 에러코드 나열")
    @ApiErrorCodeExample(CommentErrorCode.class)
    public void getCommentErrorCode() {}

    @GetMapping("/interviewer")
    @Operation(summary = "면접관 등록 관련 에러코드 나열")
    @ApiErrorCodeExample(InterviewerErrorCode.class)
    public void getInterviewerErrorCode() {}

    @GetMapping("/label")
    @Operation(summary = "라벨 등록 관련 에러코드 나열")
    @ApiErrorCodeExample(LabelErrorCode.class)
    public void getLabelErrorCode() {}

    @GetMapping("/records")
    @Operation(summary = "면접기록 등록 관련 에러코드 나열")
    @ApiErrorCodeExample(RecordErrorCode.class)
    public void getRecordErrorCode() {}

    @GetMapping("/score")
    @Operation(summary = "면접점수 등록 관련 에러코드 나열")
    @ApiErrorCodeExample(ScoreErrorCode.class)
    public void getScoreErrorCode() {}
}
