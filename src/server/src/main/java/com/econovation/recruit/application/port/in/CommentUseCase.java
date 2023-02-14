package com.econovation.recruit.application.port.in;

import com.econovation.recruit.domain.comment.Comment;
import com.econovation.recruit.domain.dto.CommentRegisterDto;

public interface CommentUseCase {
    Comment saveComment(CommentRegisterDto commentRegisterDto);
    Boolean deleteComment();

    Comment findById(Integer commentId);
}
