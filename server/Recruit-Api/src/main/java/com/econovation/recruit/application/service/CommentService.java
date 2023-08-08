package com.econovation.recruit.application.service;


import com.econovation.recruit.application.port.in.CommentUseCase;
import com.econovation.recruitdomain.domains.applicant.Applicant;
import com.econovation.recruitdomain.domains.comment.Comment;
import com.econovation.recruitdomain.domains.comment.CommentLike;
import com.econovation.recruitdomain.out.ApplicantLoadPort;
import com.econovation.recruitdomain.out.CommentLikeLoadPort;
import com.econovation.recruitdomain.out.CommentLikeRecordPort;
import com.econovation.recruitdomain.out.CommentLoadPort;
import com.econovation.recruitdomain.out.CommentRecordPort;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService implements CommentUseCase {
    private final CommentRecordPort commentRecordPort;
    private final CommentLoadPort commentLoadPort;
    private final CommentLikeRecordPort commentLikeRecordPort;
    private final CommentLikeLoadPort commentLikeLoadPort;
    private final ApplicantLoadPort applicantLoadPort;

    @Override
    @Transactional
    public Comment saveComment(Comment comment) {
        Comment loadedComment = commentRecordPort.saveComment(comment);
        // Applcant의 comment_count 추가
        Applicant applicant = applicantLoadPort.loadApplicantById(comment.getApplicant().getId());
        applicant.plusCommentCount();
        return loadedComment;
    }

    @Override
    public Boolean deleteComment() {
        return null;
    }

    @Override
    public Comment findById(Integer commentId) {
        return commentLoadPort.findById(commentId);
    }

    @Override
    public void createCommentLike(Comment comment, Integer idpId) {
        CommentLike commentLike = CommentLike.builder().comment(comment).idpId(idpId).build();
        commentLikeRecordPort.saveCommentLike(commentLike);
    }

    @Override
    public void deleteCommentLike(Comment comment) {
        CommentLike commentLike = commentLikeLoadPort.getByComment(comment);
        commentLikeRecordPort.deleteCommentLike(commentLike);
    }

    @Override
    public List<Comment> findAll() {
        return commentLoadPort.findAll();
    }

    @Override
    public Boolean isCheckedLike(Integer commentId, Integer idpId) {
        return commentLikeLoadPort.getByIdpId(idpId);
    }
}
