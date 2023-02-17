package com.econovation.recruit.application.service;

import com.econovation.recruit.application.port.in.CommentUseCase;
import com.econovation.recruit.application.port.out.*;
import com.econovation.recruit.domain.applicant.Applicant;
import com.econovation.recruit.domain.comment.Comment;
import com.econovation.recruit.domain.comment.CommentLike;
import com.econovation.recruit.domain.dto.CommentRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService implements CommentUseCase {
    private final CommentRecordPort commentRecordPort;
    private final CommentLoadPort commentLoadPort;
    private final CommentLikeRecordPort commentLikeRecordPort;

    private final CommentLikeLoadPort commentLikeLoadPort;
    private final LoadApplicantPort loadApplicantPort;
    @Override
    @Transactional
    public Comment saveComment(CommentRegisterDto commentRegisterDto) {
        Applicant applicant = loadApplicantPort.loadApplicantById(commentRegisterDto.getApplicantId());
        Comment comment = Comment.builder()
                .applicant(applicant)
                .content(commentRegisterDto.getContent())
                .isDeleted(false)
                .parentId(commentRegisterDto.getParentId())
                .build();
        commentRecordPort.saveComment(comment);
        return comment;
    }

    @Override
    public Boolean deleteComment() {
        return null;
    }

    @Override
    public Comment findById(Integer commentId) {
        return commentLoadPort.findById(commentId);
    }

    @Override
    public void createCommentLike(Comment comment, Integer idpId) {
        CommentLike commentLike = CommentLike.builder()
                .idpId(idpId)
                .comment(comment)
                .build();
        commentLikeRecordPort.saveCommentLike(commentLike);
    }

    @Override
    public void deleteCommentLike(Comment comment) {
        CommentLike commentLike = commentLikeLoadPort.getByComment(comment);
        commentLikeRecordPort.deleteCommentLike(commentLike);
    }
}
