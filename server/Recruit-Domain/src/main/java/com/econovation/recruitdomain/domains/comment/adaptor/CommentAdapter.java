package com.econovation.recruitdomain.domains.comment.adaptor;

import static com.econovation.recruitcommon.consts.RecruitStatic.NO_MATCH_COMMENT_MESSAGE;

import com.econovation.recruitcommon.annotation.Adaptor;
import com.econovation.recruitcommon.utils.Result;
import com.econovation.recruitdomain.domains.card.exception.CardNotFoundException;
import com.econovation.recruitdomain.domains.comment.domain.Comment;
import com.econovation.recruitdomain.domains.comment.domain.CommentLike;
import com.econovation.recruitdomain.domains.comment.domain.CommentLikeRepository;
import com.econovation.recruitdomain.domains.comment.domain.CommentRepository;
import com.econovation.recruitdomain.out.CommentLikeLoadPort;
import com.econovation.recruitdomain.out.CommentLikeRecordPort;
import com.econovation.recruitdomain.out.CommentLoadPort;
import com.econovation.recruitdomain.out.CommentRecordPort;
import java.util.List;
import lombok.RequiredArgsConstructor;

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
    public void deleteComment(Comment comment) {
        commentRepository.delete(comment);
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
        if (comments.isEmpty()) {
            throw CardNotFoundException.EXCEPTION;
        }
        return comments;
    }

    @Override
    public List<Comment> findByApplicantId(String applicantId) {
        List<Comment> comments = commentRepository.findByApplicantId(applicantId);
        if (comments.isEmpty()) {
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
    public void deleteAll(List<CommentLike> commentLikes) {
        commentLikeRepository.deleteAll(commentLikes);
    }

    @Override
    public List<CommentLike> getByCommentId(Long commentId) {
        return commentLikeRepository.findByCommentId(commentId);
    }

    @Override
    public Boolean getByIdpId(Long idpId) {
        return commentLikeRepository.existsByIdpId(idpId);
    }

    @Override
    public Result<CommentLike> getByCommentIdAndIdpId(Long commentId, Long idpId) {
        return Result.of(commentLikeRepository.findByCommentIdAndIdpId(commentId, idpId).get());
    }

    @Override
    public List<CommentLike> findByCommentIds(List<Long> commentIds) {
        return commentLikeRepository.findByCommentIdIn(commentIds);
    }
}
