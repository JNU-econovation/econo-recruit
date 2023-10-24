package com.econovation.recruitdomain.out;

import com.econovation.recruitdomain.domains.comment.domain.CommentLike;
import java.util.List;

public interface CommentLikeRecordPort {
    CommentLike saveCommentLike(CommentLike commentLike);

    void deleteCommentLike(CommentLike commentLike);

    void deleteAll(List<CommentLike> commentLikes);
}
