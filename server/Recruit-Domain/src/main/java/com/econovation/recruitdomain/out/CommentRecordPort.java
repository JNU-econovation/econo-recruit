package com.econovation.recruitdomain.out;

import com.econovation.recruitdomain.domains.comment.domain.Comment;
import java.util.List;

public interface CommentRecordPort {
    Comment saveComment(Comment comment);

    void deleteComment(Comment comment);

    void deleteCommentsAll(List<Comment> comments);
}
