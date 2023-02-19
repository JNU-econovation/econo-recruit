package com.econovation.recruit.adapter.out.persistence;

import com.econovation.recruit.application.port.out.CommentLikeLoadPort;
import com.econovation.recruit.application.port.out.CommentLikeRecordPort;
import com.econovation.recruit.application.port.out.CommentLoadPort;
import com.econovation.recruit.application.port.out.CommentRecordPort;
import com.econovation.recruit.domain.comment.Comment;
import com.econovation.recruit.domain.comment.CommentLike;
import com.econovation.recruit.domain.comment.CommentLikeRepository;
import com.econovation.recruit.domain.comment.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CommentPersistencAdapter implements CommentRecordPort, CommentLoadPort, CommentLikeRecordPort, CommentLikeLoadPort {
    private final CommentRepository commentRepository;
    private final CommentLikeRepository commentLikeRepository;

    private final String NO_MATCH_COMMENT_MESSAGE = "조회된 Comment가 존재하지 않습니다";
    private final String NO_MATCH_COMMENTLIKE_MESSAGE = "조회된 Comment가 존재하지 않습니다";


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

    @Override
    public List<Comment> findAll() {
        List<Comment> all = commentRepository.findAll();
        if (all.isEmpty()) {
            throw new IllegalArgumentException(NO_MATCH_COMMENT_MESSAGE);
        }
        return all;
    }

    @Override
    public CommentLike saveCommentLike(CommentLike commentLike) {
        return commentLikeRepository.save(commentLike);
    }

    @Override
    public void deleteCommentLike(CommentLike commentLike) {
        commentLikeRepository.delete(commentLike);
    }

    @Override
    public CommentLike getByComment(Comment comment) {
        return commentLikeRepository.findByComment(comment).orElseThrow(() -> new IllegalArgumentException(NO_MATCH_COMMENTLIKE_MESSAGE));
    }

    @Override
    public Boolean getByIdpId(Integer idpId) {
        return commentLikeRepository.existsByIdpId(idpId);
    }
}
