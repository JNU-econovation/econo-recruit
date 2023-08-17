package com.econovation.recruit.api.comment.usecase;

import com.econovation.recruitcommon.annotation.UseCase;
import com.econovation.recruitdomain.domains.comment.Comment;
import java.util.List;

@UseCase
public interface CommentUseCase {
    Comment saveComment(Comment comment);

    Boolean deleteComment();

    Comment findById(Comment commentId);

    void createCommentLike(Comment comment, Comment idpId);

    void deleteCommentLike(Comment comment);

    List<Comment> findAll();

    Boolean isCheckedLike(Comment commentId, Comment idpId);
}
