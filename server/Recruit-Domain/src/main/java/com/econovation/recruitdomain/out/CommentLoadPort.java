package com.econovation.recruitdomain.out;


import com.econovation.recruitdomain.domains.comment.Comment;
import java.util.List;

public interface CommentLoadPort {
    Comment findById(Integer commentId);

    List<Comment> findAll();
}
