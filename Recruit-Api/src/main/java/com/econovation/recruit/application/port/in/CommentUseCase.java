package com.econovation.recruit.application.port.in;

import com.econovation.recruit.domain.comment.Comment;

import java.util.List;

public interface CommentUseCase {
    Comment saveComment(Comment comment);
    Boolean deleteComment();
    Comment findById(Integer commentId);

    void createCommentLike(Comment comment,Integer idpId);

    void deleteCommentLike(Comment comment);

    List<Comment> findAll();

    Boolean isCheckedLike(Integer commentId, Integer idpId);
}
