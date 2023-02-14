package com.econovation.recruit.adapter.in.controller;

import com.econovation.recruit.application.port.in.CommentUseCase;
import com.econovation.recruit.application.utils.EntityMapper;
import com.econovation.recruit.domain.comment.Comment;
import com.econovation.recruit.domain.dto.CommentRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vi")
@RequiredArgsConstructor
public class CommentController {
    private final CommentUseCase commentUseCase;
    @PostMapping("/comments")
    public ResponseEntity<Comment> createComment(CommentRegisterDto commentRegisterDto) {
        Comment comment = commentUseCase.saveComment(commentRegisterDto);
//        CommentResponseDto commentResponseDto = entityMapper.toCommentResponseDto(comment);
        return new ResponseEntity(comment, HttpStatus.OK);
    }

    @PostMapping("/comments/delete")
    public ResponseEntity<Integer> deleteComment(){
        // 코멘트가 있는지 확인

        // 코멘트가 있으면
        // 코멘트의 isDeleted false -> true
        // 코멘트와 연결된 Applicant 테이블 조회
        // Applicant Comment_Count 1--
    }
}
