package com.econovation.recruit.application.port.out;

import com.econovation.recruit.domain.comment.Comment;
import com.econovation.recruit.domain.comment.CommentLike;

public interface CommentLikeLoadPort {
    void deleteCommentLike(CommentLike commentLike);

    CommentLike getByComment(Comment comment);

    Boolean getByIdpId(Integer idpId);
}
