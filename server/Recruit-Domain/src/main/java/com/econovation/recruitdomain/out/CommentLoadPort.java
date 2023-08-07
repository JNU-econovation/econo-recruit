package com.econovation.recruitdomain.out;


import com.econovation.recruitdomain.domain.comment.Comment;
import java.util.List;

public interface CommentLoadPort {
    Comment findById(Integer commentId);

    List<Comment> findAll();
}
