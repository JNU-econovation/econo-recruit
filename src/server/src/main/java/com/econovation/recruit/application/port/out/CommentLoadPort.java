package com.econovation.recruit.application.port.out;

import com.econovation.recruit.domain.comment.Comment;

public interface CommentLoadPort {
    Comment findById(Integer commentId);
}
