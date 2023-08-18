package com.econovation.recruit.api.comment.usecase;

import com.econovation.recruitcommon.annotation.UseCase;
import com.econovation.recruitdomain.domains.comment.Comment;
import com.econovation.recruitdomain.domains.dto.CommentPairVo;
import java.util.List;

@UseCase
public interface CommentUseCase {
    Comment saveComment(Comment comment);

    Boolean deleteComment();

    Comment findById(Long commentId);

    void createCommentLike(Long commentId, Long idpId);

    void deleteCommentLike(Comment comment);

    List<Comment> findAll();
    List<CommentPairVo> findByCardId(Long cardId);

    Boolean isCheckedLike(Long commentId, Long idpId);
}
