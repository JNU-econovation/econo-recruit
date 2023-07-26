package com.econovation.recruitdomain.out;


import com.econovation.recruitdomain.domain.comment.Comment;
import com.econovation.recruitdomain.domain.comment.CommentLike;

public interface CommentLikeLoadPort {
    void deleteCommentLike(CommentLike commentLike);

    CommentLike getByComment(Comment comment);

    Boolean getByIdpId(Integer idpId);
}
