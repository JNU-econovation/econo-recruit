package com.econovation.recruit.api.comment.service;

import com.econovation.recruit.api.comment.usecase.CommentUseCase;
import com.econovation.recruit.config.security.SecurityUtils;
import com.econovation.recruitcommon.utils.Result;
import com.econovation.recruitdomain.domains.card.Card;
import com.econovation.recruitdomain.domains.comment.domain.Comment;
import com.econovation.recruitdomain.domains.comment.domain.CommentLike;
import com.econovation.recruitdomain.domains.dto.CommentPairVo;
import com.econovation.recruitdomain.domains.interviewer.domain.Interviewer;
import com.econovation.recruitdomain.out.CardLoadPort;
import com.econovation.recruitdomain.out.CommentLikeLoadPort;
import com.econovation.recruitdomain.out.CommentLikeRecordPort;
import com.econovation.recruitdomain.out.CommentLoadPort;
import com.econovation.recruitdomain.out.CommentRecordPort;
import com.econovation.recruitdomain.out.InterviewerLoadPort;
import java.util.List;
import java.util.stream.Collectors;
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
    private final InterviewerLoadPort interviewerLoadPort;

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
    public Comment findById(Long commentId) {
        return commentLoadPort.findById(commentId);
    }

    @Override
    public void createCommentLike(Long commentId, Long idpId) {
        CommentLike commentLike = CommentLike.builder().commentId(commentId).idpId(idpId).build();
        commentLikeRecordPort.saveCommentLike(commentLike);
    }

    @Override
    public void deleteCommentLike(Long commentId) {
        CommentLike commentLike = commentLikeLoadPort.getByCommentId(commentId);
        commentLikeRecordPort.deleteCommentLike(commentLike);
    }

    @Override
    public List<Comment> findAll() {
        return commentLoadPort.findAll();
    }

    @Override
    public List<CommentPairVo> findByCardId(Long cardId) {
        Long currentUserId = SecurityUtils.getCurrentUserId();
        Card card = cardLoadPort.findById(cardId);
        List<Comment> comments = commentLoadPort.findByCardId(card.getId());

        List<Long> commentIdpIds = comments.stream()
                .map(Comment::getIdpId)
                .collect(Collectors.toList());

        List<Interviewer> interviewers = interviewerLoadPort.loadInterviewerByIdpIds(commentIdpIds);

        return comments.stream()
                .map(comment -> {
                    boolean isLiked = commentLikeLoadPort.getByIdpId(currentUserId);
                    String interviewerName = interviewers.stream()
                            .filter(interviewer -> interviewer.getId().equals(comment.getIdpId()))
                            .findFirst()
                            .map(Interviewer::getName)
                            .orElse("");

                    return CommentPairVo.of(comment, isLiked, interviewerName);
                })
                .collect(Collectors.toList());
    }

    @Override
    public Boolean isCheckedLike(Long commentId) {
        Long idpId = SecurityUtils.getCurrentUserId();
        return commentLikeLoadPort.getByCommentIdAndIdpId(commentId, idpId)
                .isSuccess();
    }
}
