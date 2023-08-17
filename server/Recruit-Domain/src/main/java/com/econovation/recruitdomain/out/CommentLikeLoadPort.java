package com.econovation.recruitdomain.out;

import com.econovation.recruitdomain.domains.comment.Comment;
import com.econovation.recruitdomain.domains.comment.CommentLike;

public interface CommentLikeLoadPort {
    void deleteCommentLike(CommentLike commentLike);

    CommentLike getByCommentId(Comment commentId);

    Boolean getByIdpId(Comment idpId);
}
