package com.econovation.recruit.application.port.out;

import com.econovation.recruit.domain.comment.Comment;

import java.util.List;

public interface CommentLoadPort {
    Comment findById(Integer commentId);
    List<Comment> findAll();
}
