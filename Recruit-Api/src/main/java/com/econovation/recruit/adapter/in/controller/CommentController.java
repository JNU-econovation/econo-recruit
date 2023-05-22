package com.econovation.recruit.adapter.in.controller;

import com.econovation.recruit.application.port.in.CommentUseCase;
import com.econovation.recruit.application.utils.EntityMapper;
import com.econovation.recruit.domain.comment.Comment;
import com.econovation.recruit.domain.dto.CommentRegisterDto;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CommentController {
    private final CommentUseCase commentUseCase;
    private final EntityMapper entityMapper;

    @PostMapping("/comments")
    public ResponseEntity<Comment> createComment(CommentRegisterDto commentRegisterDto){
        Comment comment = commentUseCase.saveComment(
                entityMapper.toComment(commentRegisterDto));
        return new ResponseEntity(comment, HttpStatus.OK);
    }
    @GetMapping("/comments")
    public ResponseEntity<List<Comment>> findAll() {
        List<Comment> comments = commentUseCase.findAll();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
    @PostMapping("/comments/is/likes")
    public ResponseEntity<Boolean> isCheckedLike(Integer commentId, Integer idpId) {
        Boolean isCheck = commentUseCase.isCheckedLike(commentId,idpId);
        return new ResponseEntity(isCheck, HttpStatus.OK);
    }

    @PostMapping("/comments/delete")
    public ResponseEntity<Integer> deleteComment(Integer commentId){
        // 코멘트가 있는지 확인
        Comment comment = commentUseCase.findById(commentId);
        // 코멘트가 있으면
        if(!comment.equals(null)){
            // 코멘트의 isDeleted false -> true
            comment.delete();
            // 코멘트와 연결된 Applicant 테이블 조회
            comment.getApplicant().minusCommentCount();
            // Applicant Comment_Count 1--
        }
        return new ResponseEntity<>(commentId, HttpStatus.OK);
    }
    // 댓글 수정

    @PostMapping("/comments/likes/plus")
    public ResponseEntity plusLikeCount(Integer commentId, Integer idpId) {
        // 코멘트가 있는지 확인
        Comment comment = commentUseCase.findById(commentId);
        // 코멘트가 있으면
        if(!comment.equals(null)){
            comment.plusLikeCount();
            commentUseCase.createCommentLike(comment,idpId);
        }
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @PostMapping("/comments/likes/minus")
    public ResponseEntity minusLikeCount(Integer commentId){
        // 코멘트가 있는지 확인
        Comment comment = commentUseCase.findById(commentId);
        // 코멘트가 있으면
        if(!comment.equals(null)){
            comment.minusLikeCount();
            commentUseCase.deleteCommentLike(comment);
        }
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }
}
