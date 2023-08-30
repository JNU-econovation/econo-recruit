package com.econovation.recruit.api.comment.usecase;

import com.econovation.recruitcommon.annotation.UseCase;
import com.econovation.recruitdomain.domains.comment.domain.Comment;
import com.econovation.recruitdomain.domains.dto.CommentPairVo;
import java.util.List;

@UseCase
public interface CommentUseCase {
    Comment saveComment(Comment comment);

    void deleteComment(Long commentId);

    Comment findById(Long commentId);

    void createCommentLike(Long commentId);

    void deleteCommentLike(Long commentId);

    List<CommentPairVo> findByCardId(Long cardId);

    Boolean isCheckedLike(Long commentId);

    void updateCommentContent(Long commentId, String content);

    List<CommentPairVo> findByApplicantId(String applicantId);
}
