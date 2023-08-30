package com.econovation.recruit.api.comment.service;

import com.econovation.recruit.api.comment.usecase.CommentUseCase;
import com.econovation.recruit.api.config.security.SecurityUtils;
import com.econovation.recruitcommon.utils.Result;
import com.econovation.recruitdomain.common.aop.redissonLock.RedissonLock;
import com.econovation.recruitdomain.domains.card.domain.Card;
import com.econovation.recruitdomain.domains.comment.domain.Comment;
import com.econovation.recruitdomain.domains.comment.domain.CommentLike;
import com.econovation.recruitdomain.domains.comment.exception.CommentNotHostException;
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
        // 지원서 카드면 카드 타입이지만
        if (comment.isApplicantComment()) {
            Card card = cardLoadPort.findByApplicantId(comment.getApplicantId());
            card.plusCommentCount();
            return loadedComment;
        }
        // 카드 타입이면서 지원서 타입이면 안된다.
        else {
            Card card = cardLoadPort.findById(comment.getCardId());
            card.plusCommentCount();
            return loadedComment;
        }
    }

    @Override
    @Transactional
    public void deleteComment(Long commentId) {
        Long idpId = SecurityUtils.getCurrentUserId();
        Comment comment = commentLoadPort.findById(commentId);
        // 본인이 만든 댓글만 삭제가 가능하다
        if (comment.isHost(idpId)) {
            commentRecordPort.deleteComment(comment);
            // 관련된 commentLike 삭제 처리
            // TODO : Event 처리로 변경
            List<CommentLike> commentLikes = commentLikeLoadPort.getByCommentId(commentId);
            commentLikeRecordPort.deleteAll(commentLikes);
        } else {
            throw CommentNotHostException.EXCEPTION;
        }
    }

    @Override
    public Comment findById(Long commentId) {
        return commentLoadPort.findById(commentId);
    }

    @Override
    @RedissonLock(LockName = "댓글좋아요", identifier = "commentId")
    public void createCommentLike(Long commentId) {
        // 기존에 눌렀으면 취소 처리
        Long idpId = SecurityUtils.getCurrentUserId();
        Result<CommentLike> commentLikeResult =
                commentLikeLoadPort.getByCommentIdAndIdpId(commentId, idpId);
        Comment comment = commentLoadPort.findById(commentId);
        if (commentLikeResult.isSuccess()) {
            updateCommentLikeAndDelete(comment, commentLikeResult.getValue());
        } else {
            updateCommentLikeAndSave(comment, idpId);
        }
    }

    private void updateCommentLikeAndDelete(Comment comment, CommentLike commentLike) {
        comment.minusLikeCount();
        commentLikeRecordPort.deleteCommentLike(commentLike);
    }

    private void updateCommentLikeAndSave(Comment comment, Long idpId) {
        comment.plusLikeCount();
        CommentLike newCommentLike =
                CommentLike.builder().commentId(comment.getId()).idpId(idpId).build();
        commentLikeRecordPort.saveCommentLike(newCommentLike);
    }

    @Override
    @RedissonLock(LockName = "댓글좋아요", identifier = "commentId")
    public void deleteCommentLike(Long commentId) {
        // 현재 내가 눌렀던 댓글만 삭제할 수 있다.
        Long idpId = SecurityUtils.getCurrentUserId();
        Comment comment = commentLoadPort.findById(commentId);
        commentLikeLoadPort
                .getByCommentIdAndIdpId(commentId, idpId)
                .onSuccess(
                        commentLike -> {
                            commentLikeRecordPort.deleteCommentLike(commentLike);
                            comment.minusLikeCount();
                        });
    }

    @Override
    public List<CommentPairVo> findByCardId(Long cardId) {
        Long currentUserId = SecurityUtils.getCurrentUserId();
        Card card = cardLoadPort.findById(cardId);
        List<Comment> comments = commentLoadPort.findByCardId(card.getId());

        List<Long> commentIdpIds =
                comments.stream().map(Comment::getIdpId).collect(Collectors.toList());

        List<Interviewer> interviewers = interviewerLoadPort.loadInterviewerByIdpIds(commentIdpIds);

        return comments.stream()
                .map(
                        comment -> {
                            boolean isLiked = commentLikeLoadPort.getByIdpId(currentUserId);
                            String interviewerName =
                                    interviewers.stream()
                                            .filter(
                                                    interviewer ->
                                                            interviewer
                                                                    .getId()
                                                                    .equals(comment.getIdpId()))
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
        return commentLikeLoadPort.getByCommentIdAndIdpId(commentId, idpId).isSuccess();
    }

    @Override
    @Transactional
    public void updateCommentContent(Long commentId, String content) {
        // 내가 작성한 comment 만 수정할 수 있다.
        Long idpId = SecurityUtils.getCurrentUserId();
        Comment comment = commentLoadPort.findById(commentId);
        if (comment.getIdpId().equals(idpId)) {
            comment.updateContent(content);
        } else {
            throw CommentNotHostException.EXCEPTION;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentPairVo> findByApplicantId(String applicantId) {
        Long idpId = SecurityUtils.getCurrentUserId();
        List<Comment> comments = commentLoadPort.findByApplicantId(applicantId);
        List<Long> commentIds = comments.stream().map(Comment::getId).collect(Collectors.toList());
        List<Interviewer> interviewers = interviewerLoadPort.loadInterviewerByIdpIds(commentIds);
        List<CommentLike> commentLikes = commentLikeLoadPort.findByCommentIds(commentIds);
        return comments.stream()
                .map(
                        comment -> {
                            Boolean isLiked =
                                    commentLikes.stream()
                                            .anyMatch(
                                                    commentLike ->
                                                            commentLike
                                                                            .getCommentId()
                                                                            .equals(comment.getId())
                                                                    && commentLike
                                                                            .getIdpId()
                                                                            .equals(idpId));
                            String interviewersName =
                                    interviewers.stream()
                                            .filter(
                                                    interviewer ->
                                                            interviewer
                                                                    .getId()
                                                                    .equals(comment.getIdpId()))
                                            .findFirst()
                                            .map(Interviewer::getName)
                                            .orElse("");
                            return CommentPairVo.of(comment, isLiked, interviewersName);
                        })
                .collect(Collectors.toList());
    }
}
