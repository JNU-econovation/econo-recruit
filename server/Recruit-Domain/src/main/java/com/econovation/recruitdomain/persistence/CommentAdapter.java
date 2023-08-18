package com.econovation.recruitdomain.persistence;

import com.econovation.recruitcommon.annotation.Adaptor;
import com.econovation.recruitdomain.domains.card.Card;
import com.econovation.recruitdomain.domains.card.exception.CardNotFoundException;
import com.econovation.recruitdomain.domains.comment.Comment;
import com.econovation.recruitdomain.domains.comment.CommentLike;
import com.econovation.recruitdomain.domains.comment.CommentLikeRepository;
import com.econovation.recruitdomain.domains.comment.CommentRepository;
import com.econovation.recruitdomain.out.CommentLikeLoadPort;
import com.econovation.recruitdomain.out.CommentLikeRecordPort;
import com.econovation.recruitdomain.out.CommentLoadPort;
import com.econovation.recruitdomain.out.CommentRecordPort;
import java.util.List;
import lombok.RequiredArgsConstructor;

import static com.econovation.recruitcommon.consts.RecruitStatic.NO_MATCH_COMMENTLIKE_MESSAGE;
import static com.econovation.recruitcommon.consts.RecruitStatic.NO_MATCH_COMMENT_MESSAGE;

@Adaptor
@RequiredArgsConstructor
public class CommentAdapter
        implements CommentRecordPort, CommentLoadPort, CommentLikeRecordPort, CommentLikeLoadPort {
    private final CommentRepository commentRepository;
    private final CommentLikeRepository commentLikeRepository;

    @Override
    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment findById(Long commentId) {
        return commentRepository
                .findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException(NO_MATCH_COMMENT_MESSAGE));
    }

    @Override
    public List<Comment> findAll() {
        List<Comment> all = commentRepository.findAll();
        if (all.isEmpty()) {
            throw new IllegalArgumentException(NO_MATCH_COMMENT_MESSAGE);
        }
        return all;
    }

    @Override
    public List<Comment> findByCardId(Long cardId) {
        List<Comment> comments = commentRepository.findByCardId(cardId);
        if(comments.isEmpty()){
            throw CardNotFoundException.EXCEPTION;
        }
        return comments;
    }

    @Override
    public CommentLike saveCommentLike(CommentLike commentLike) {
        return commentLikeRepository.save(commentLike);
    }

    @Override
    public void deleteCommentLike(CommentLike commentLike) {
        commentLikeRepository.delete(commentLike);
    }

    @Override
    public CommentLike getByCommentId(Long commentId) {
        return commentLikeRepository
                .findByCommentId(commentId)
                .orElseThrow(() -> new IllegalArgumentException(NO_MATCH_COMMENTLIKE_MESSAGE));
    }

    @Override
    public Boolean getByIdpId(Long idpId) {
        return commentLikeRepository.existsByIdpId(idpId);
    }
}
