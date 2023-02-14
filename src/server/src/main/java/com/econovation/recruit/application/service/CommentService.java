package com.econovation.recruit.application.service;

import com.econovation.recruit.application.port.in.CommentUseCase;
import com.econovation.recruit.application.port.out.CommentLoadPort;
import com.econovation.recruit.application.port.out.CommentRecordPort;
import com.econovation.recruit.application.port.out.LoadApplicantPort;
import com.econovation.recruit.domain.applicant.Applicant;
import com.econovation.recruit.domain.comment.Comment;
import com.econovation.recruit.domain.dto.CommentRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService implements CommentUseCase {
    private final CommentRecordPort commentRecordPort;
    private final CommentLoadPort commentLoadPort;
    private final LoadApplicantPort loadApplicantPort;
    @Override
    @Transactional
    public Comment saveComment(CommentRegisterDto commentRegisterDto) {
        Applicant applicant = loadApplicantPort.loadApplicantById(commentRegisterDto.getApplicantId());
        Comment comment = Comment.builder()
                .applicant(applicant)
                .content(commentRegisterDto.getContent())
                .isDeleted(false)
                .parentId(commentRegisterDto.getParentId())
                .build();
        commentRecordPort.saveComment(comment);
        return comment;
    }

    @Override
    public Boolean deleteComment() {
        return null;
    }

    @Override
    public Comment findById(Integer commentId) {
        return commentLoadPort.findById(commentId);
    }
}
