package com.econovation.recruitdomain.out;

import com.econovation.recruitcommon.utils.Result;
import com.econovation.recruitdomain.domains.comment.domain.CommentLike;

public interface CommentLikeLoadPort {
    void deleteCommentLike(CommentLike commentLike);

    CommentLike getByCommentId(Long commentId);

    Boolean getByIdpId(Long idpId);

    Result<CommentLike> getByCommentIdAndIdpId(Long commentId, Long idpId);
}
