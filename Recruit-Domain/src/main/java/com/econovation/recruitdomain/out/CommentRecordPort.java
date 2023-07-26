package com.econovation.recruitdomain.out;


import com.econovation.recruitdomain.domain.comment.Comment;

public interface CommentRecordPort {
    Comment saveComment(Comment comment);
}
