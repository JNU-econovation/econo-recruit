package com.econovation.recruit.api.comment.usecase;

import com.econovation.recruitcommon.annotation.UseCase;
import com.econovation.recruitdomain.domains.comment.domain.Comment;
import com.econovation.recruitdomain.domains.dto.CommentPairVo;
import com.econovation.recruitdomain.domains.dto.CommentRegisterDto;
import java.util.List;
import java.util.Map;

@UseCase
public interface CommentUseCase {
    Comment saveComment(CommentRegisterDto comment);

    void deleteComment(Long commentId);

    Comment findById(Long commentId);

    void createCommentLike(Long commentId);

    void deleteCommentLike(Long commentId);

    List<CommentPairVo> findByCardId(Long cardId);

    Boolean isCheckedLike(Long commentId);

    void updateCommentContent(Long commentId, Map<String, String> contents);

    List<CommentPairVo> findByApplicantId(String applicantId);

    void deleteCommentByCardId(Long cardId);
}
