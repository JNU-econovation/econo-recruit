package com.econovation.recruit.api.comment.controller;

import static com.econovation.recruitcommon.consts.RecruitStatic.*;

import com.econovation.recruit.api.comment.docs.CommentExceptionDocs;
import com.econovation.recruit.api.comment.docs.CommentLikeExceptionDocs;
import com.econovation.recruit.api.comment.usecase.CommentUseCase;
import com.econovation.recruitcommon.annotation.ApiErrorExceptionsExample;
import com.econovation.recruitdomain.domains.dto.CommentPairVo;
import com.econovation.recruitdomain.domains.dto.CommentRegisterDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SecurityRequirement(name = "access-token")
@RestController
@RequestMapping("/api/v1")
@Tag(name = "[3.0] Comment 관련 API", description = "댓글(Comment) API")
@RequiredArgsConstructor
public class CommentController {
    private final CommentUseCase commentUseCase;

    @Operation(summary = "지원서 카드에 댓글 등록")
    @ApiErrorExceptionsExample(CommentExceptionDocs.class)
    @PostMapping("/comments")
    public ResponseEntity createComment(@RequestBody CommentRegisterDto commentRegisterDto) {
        commentUseCase.saveComment(commentRegisterDto);
        return new ResponseEntity(COMMENT_SUCCESS_REGISTER_MESSAGE, HttpStatus.OK);
    }

    @Operation(summary = "cardId 로 댓글 조회")
    @ApiErrorExceptionsExample(CommentExceptionDocs.class)
    @GetMapping("/cards/{card-id}/comments")
    public ResponseEntity<List<CommentPairVo>> findByCardId(
            @PathVariable(name = "card-id") Long cardId) {
        List<CommentPairVo> comments = commentUseCase.findByCardId(cardId);
        return new ResponseEntity(comments, HttpStatus.OK);
    }

    @Operation(summary = "applicationId로 댓글 조회")
    @GetMapping("/applicants/{applicant-id}/comments")
    public ResponseEntity<List<CommentPairVo>> findByApplicantId(
            @PathVariable(name = "applicant-id") String applicantId) {
        List<CommentPairVo> comments = commentUseCase.findByApplicantId(applicantId);
        return new ResponseEntity(comments, HttpStatus.OK);
    }

    @Operation(summary = "댓글 좋아요")
    @ApiErrorExceptionsExample(CommentLikeExceptionDocs.class)
    @PostMapping("/comments/{comment-id}/likes")
    public ResponseEntity plusLikeCount(@PathVariable(name = "comment-id") Long commentId) {
        commentUseCase.createCommentLike(commentId);
        return new ResponseEntity<>(COMMENT_LIKE_SUCCESS_REGISTER_MESSAGE, HttpStatus.OK);
    }

    @Operation(summary = "댓글 좋아요 눌렀는지 확인")
    @GetMapping("/comments/{comment-id}/is-like")
    public ResponseEntity<Boolean> isCheckedLike(
            @PathVariable(name = "comment-id") Long commentId) {
        Boolean isCheck = commentUseCase.isCheckedLike(commentId);
        return new ResponseEntity(isCheck, HttpStatus.OK);
    }

    @Operation(summary = "댓글 삭제")
    @DeleteMapping("/comments/{comment-id}")
    public ResponseEntity<Long> deleteComment(@PathVariable(name = "comment-id") Long commentId) {
        commentUseCase.deleteComment(commentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "댓글 수정")
    @PutMapping("/comments/{comment-id}")
    public ResponseEntity updateComment(
            @PathVariable(name = "comment-id") Long commentId, @RequestBody Map<String, String> content) {
        commentUseCase.updateCommentContent(commentId, content);
        return new ResponseEntity<>(COMMENT_SUCCESS_UPDATE_MESSAGE, HttpStatus.OK);
    }

    @Operation(summary = "댓글 좋아요 취소", description = "댓글 좋아요 취소 -> 댓글 좋아요 한번 더 눌러도 동일합니다.")
    @DeleteMapping("/comments/{comment-id}/likes")
    public ResponseEntity minusLikeCount(@PathVariable(name = "comment-id") Long commentId) {
        commentUseCase.deleteCommentLike(commentId);
        return new ResponseEntity<>(COMMENT_LIKE_SUCCESS_DELETE_MESSAGE, HttpStatus.OK);
    }
}
