package com.econovation.recruit.api.comment.controller;

import com.econovation.recruit.api.comment.docs.CommentExceptionDocs;
import com.econovation.recruit.api.comment.usecase.CommentUseCase;
import com.econovation.recruitcommon.annotation.ApiErrorExceptionsExample;
import com.econovation.recruitdomain.domains.comment.domain.Comment;
import com.econovation.recruitdomain.domains.dto.CommentPairVo;
import com.econovation.recruitdomain.domains.dto.CommentRegisterDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.econovation.recruitcommon.consts.RecruitStatic.COMMENT_LIKE_SUCCESS_REGISTER_MESSAGE;
import static com.econovation.recruitcommon.consts.RecruitStatic.COMMENT_SUCCESS_REGISTER_MESSAGE;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "[3.0] Comment 관련 API", description = "댓글(Comment) API")
@RequiredArgsConstructor
public class CommentController {
    private final CommentUseCase commentUseCase;

    @Operation(summary = "댓글 등록")
    @ApiErrorExceptionsExample(CommentExceptionDocs.class)
    @PostMapping("/comments")
    public ResponseEntity createComment(CommentRegisterDto commentRegisterDto) {
        commentUseCase.saveComment(CommentRegisterDto.from(commentRegisterDto));
        return new ResponseEntity(COMMENT_SUCCESS_REGISTER_MESSAGE, HttpStatus.OK);
    }

    @Operation(summary = "댓글 조회")
    @GetMapping("/comments/{cardId}")
    public ResponseEntity<List<CommentPairVo>> findByCardId(Long cardId) {
        List<CommentPairVo> comments = commentUseCase.findByCardId(cardId);
        return new ResponseEntity(comments, HttpStatus.OK);
    }

    @Operation(summary = "댓글 좋아요")
    @PostMapping("/comments/likes")
    public ResponseEntity plusLikeCount(Long commentId) {
        commentUseCase.createCommentLike(commentId);
        return new ResponseEntity<>(COMMENT_LIKE_SUCCESS_REGISTER_MESSAGE, HttpStatus.OK);
    }

    @Operation(summary = "댓글 좋아요 눌렀는지 확인")
    @GetMapping("/comments/is-like")
    public ResponseEntity<Boolean> isCheckedLike(Long commentId) {
        Boolean isCheck = commentUseCase.isCheckedLike(commentId);
        return new ResponseEntity(isCheck, HttpStatus.OK);
    }

    @Operation(summary = "댓글 삭제")
    @DeleteMapping("/comments")
    public ResponseEntity<Long> deleteComment(Long commentId) {
        commentUseCase.deleteComment(commentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 댓글 수정
//    @PostMapping("/comments/likes")
//    public ResponseEntity plusLikeCount(Long commentId, Long idpId) {
//            commentUseCase.createCommentLike(commentId, idpId);
//        return new ResponseEntity<>(COMMENT_LIKE_SUCCESS_REGISTER_MESSAGE, HttpStatus.OK);
//    }

    @PostMapping("/comments/likes/minus")
    public ResponseEntity minusLikeCount(Long commentId) {
        commentUseCase.deleteCommentLike(commentId);
        return new ResponseEntity<>( HttpStatus.OK);
    }
}
