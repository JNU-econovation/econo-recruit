package com.econovation.recruit.application.port.out;

import com.econovation.recruit.domain.comment.CommentLike;

public interface CommentLikeRecordPort {
    CommentLike saveCommentLike(CommentLike commentLike);
    void deleteCommentLike(CommentLike commentLike);
}
