package com.econovation.recruit.api.comment.service;

import com.econovation.recruit.api.comment.usecase.CommentUseCase;
import com.econovation.recruitdomain.domains.card.Card;
import com.econovation.recruitdomain.domains.comment.Comment;
import com.econovation.recruitdomain.domains.comment.CommentLike;
import com.econovation.recruitdomain.out.CardLoadPort;
import com.econovation.recruitdomain.out.CommentLikeLoadPort;
import com.econovation.recruitdomain.out.CommentLikeRecordPort;
import com.econovation.recruitdomain.out.CommentLoadPort;
import com.econovation.recruitdomain.out.CommentRecordPort;
import java.util.List;
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
    private final CardLoadPort cardLoadPort;

    @Override
    @Transactional
    public Comment saveComment(Comment comment) {
        Comment loadedComment = commentRecordPort.saveComment(comment);
        Card card = cardLoadPort.findByApplicantId(comment.getApplicantId());
        card.plusCommentCount();
        return loadedComment;
    }

    @Override
    public Boolean deleteComment() {
        return null;
    }

    @Override
    public Comment findById(Comment commentId) {
        return commentLoadPort.findById(commentId);
    }

    @Override
    public void createCommentLike(Comment commentId, Comment idpId) {
        CommentLike commentLike = CommentLike.builder().commentId(commentId).idpId(idpId).build();
        commentLikeRecordPort.saveCommentLike(commentLike);
    }

    @Override
    public void deleteCommentLike(Comment comment) {
        CommentLike commentLike = commentLikeLoadPort.getByCommentId(comment);
        commentLikeRecordPort.deleteCommentLike(commentLike);
    }

    @Override
    public List<Comment> findAll() {
        return commentLoadPort.findAll();
    }

    @Override
    public Boolean isCheckedLike(Comment commentId, Comment idpId) {
        return commentLikeLoadPort.getByIdpId(idpId);
    }
}
