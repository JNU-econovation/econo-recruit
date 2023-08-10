package com.econovation.recruitdomain.out;

import com.econovation.recruitdomain.domains.comment.CommentLike;

public interface CommentLikeRecordPort {
    CommentLike saveCommentLike(CommentLike commentLike);

    void deleteCommentLike(CommentLike commentLike);
}
