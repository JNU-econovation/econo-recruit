package com.econovation.recruitdomain.domains.comment.adaptor;

import static com.econovation.recruitcommon.consts.RecruitStatic.NO_MATCH_COMMENT_MESSAGE;

import com.econovation.recruitcommon.annotation.Adaptor;
import com.econovation.recruitcommon.utils.Result;
import com.econovation.recruitdomain.domains.comment.domain.Comment;
import com.econovation.recruitdomain.domains.comment.domain.CommentLike;
import com.econovation.recruitdomain.domains.comment.domain.CommentLikeRepository;
import com.econovation.recruitdomain.domains.comment.domain.CommentRepository;
import com.econovation.recruitdomain.domains.comment.exception.CommentLikeNotFoundException;
import com.econovation.recruitdomain.domains.comment.exception.CommentNotFoundException;
import com.econovation.recruitdomain.out.CommentLikeLoadPort;
import com.econovation.recruitdomain.out.CommentLikeRecordPort;
import com.econovation.recruitdomain.out.CommentLoadPort;
import com.econovation.recruitdomain.out.CommentRecordPort;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
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
                .orElseThrow(() -> CommentNotFoundException.EXCEPTION);
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
            return Collections.emptyList();
        }
        return comments;
    }

    @Override
    public List<Comment> findByApplicantId(String applicantId) {
        List<Comment> comments = commentRepository.findByApplicantId(applicantId);
        if (comments.isEmpty()) {
            return Collections.emptyList();
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
    public void deleteByInterviewerId(Long idpId) {
        commentRepository.deleteByIdpId(idpId);
    }

    @Override
    public void deleteCommentsAll(List<Comment> comments) {
        commentRepository.deleteAll(comments);
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
        Optional<CommentLike> byCommentIdAndIdpId =
                commentLikeRepository.findByCommentIdAndIdpId(commentId, idpId);
        if (!byCommentIdAndIdpId.isPresent()) {
            return Result.failure(CommentLikeNotFoundException.EXCEPTION);
        }
        return Result.success(byCommentIdAndIdpId.get());
    }

    @Override
    public List<CommentLike> findByCommentIds(List<Long> commentIds) {
        return commentLikeRepository.findByCommentIdIn(commentIds);
    }
}
