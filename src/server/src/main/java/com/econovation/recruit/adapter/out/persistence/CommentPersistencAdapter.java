package com.econovation.recruit.adapter.out.persistence;

import com.econovation.recruit.application.port.out.CommentLoadPort;
import com.econovation.recruit.application.port.out.CommentRecordPort;
import com.econovation.recruit.domain.comment.Comment;
import com.econovation.recruit.domain.comment.CommentRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CommentPersistencAdapter implements CommentRecordPort, CommentLoadPort {
    private final CommentRepository commentRepository;
    private final String NO_MATCH_COMMENT_MESSAGE = "조회된 Comment가 존재하지 않습니다";


    @Override
    public Comment saveComment(Comment comment) {
        comment = commentRepository.save(comment);
        if (comment.equals(null)) {
            throw new IllegalArgumentException(NO_MATCH_COMMENT_MESSAGE);
        }
        return comment;
    }


    @Override
    public Comment findById(Integer commentId) {
        return commentRepository.findById(Long.valueOf(commentId))
                .orElseThrow(() -> new IllegalArgumentException(NO_MATCH_COMMENT_MESSAGE));
    }
}
