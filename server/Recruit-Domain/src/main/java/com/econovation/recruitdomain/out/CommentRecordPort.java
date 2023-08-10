package com.econovation.recruitdomain.out;

import com.econovation.recruitdomain.domains.comment.Comment;

public interface CommentRecordPort {
    Comment saveComment(Comment comment);
}
