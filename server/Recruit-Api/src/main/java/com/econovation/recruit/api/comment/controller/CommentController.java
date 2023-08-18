package com.econovation.recruit.api.comment.controller;

import com.econovation.recruit.api.comment.usecase.CommentUseCase;
import com.econovation.recruitdomain.domains.comment.Comment;
import com.econovation.recruitdomain.domains.dto.CommentPairVo;
import com.econovation.recruitdomain.domains.dto.CommentRegisterDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.econovation.recruitcommon.consts.RecruitStatic.COMMENT_SUCCESS_REGISTER_MESSAGE;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Comment 관련 API", description = "댓글(Comment) API")
@RequiredArgsConstructor
public class CommentController {
    private final CommentUseCase commentUseCase;

    @Operation(summary = "댓글 등록")
    @PostMapping("/comments")
    public ResponseEntity createComment(CommentRegisterDto commentRegisterDto) {
        commentUseCase.saveComment(CommentRegisterDto.from(commentRegisterDto));
        return new ResponseEntity(COMMENT_SUCCESS_REGISTER_MESSAGE, HttpStatus.OK);
    }

    @Operation(summary = "댓글 조회")
    @GetMapping("/comments/{cardId}")
    public ResponseEntity<List<Comment>> findByCardId(Long cardId) {
        List<CommentPairVo> comments = commentUseCase.findByCardId(cardId);
        return new ResponseEntity(comments, HttpStatus.OK);
    }

    @PostMapping("/comments/is/likes")
    public ResponseEntity<Boolean> isCheckedLike(Long commentId, Long idpId) {
        Boolean isCheck = commentUseCase.isCheckedLike(commentId, idpId);
        return new ResponseEntity(isCheck, HttpStatus.OK);
    }

    @PostMapping("/comments/delete")
    public ResponseEntity<Long> deleteComment(Long commentId) {
        // 코멘트가 있는지 확인
        Comment comment = commentUseCase.findById(commentId);
        // 코멘트가 있으면
        if (!comment.equals(null)) {
            // 코멘트의 isDeleted false -> true
            comment.delete();
            // 코멘트와 연결된 Applicant 테이블 조회
            // comment.getApplicantId().minusCommentCount();
            // Applicant Comment_Count 1--
        }
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
        // 코멘트가 있는지 확인
        Comment comment = commentUseCase.findById(commentId);
        // 코멘트가 있으면
        if (!comment.equals(null)) {
            comment.minusLikeCount();
            commentUseCase.deleteCommentLike(comment);
        }
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }
}
