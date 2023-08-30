package com.econovation.recruitdomain.out;

import com.econovation.recruitdomain.domains.comment.domain.Comment;
import java.util.List;

public interface CommentLoadPort {
    Comment findById(Long commentId);

    List<Comment> findAll();

    List<Comment> findByCardId(Long cardId);

    List<Comment> findByApplicantId(String applicantId);
}
