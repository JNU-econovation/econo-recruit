package com.econovation.recruit.api.comment.docs;

import com.econovation.recruitcommon.annotation.ExceptionDoc;
import com.econovation.recruitcommon.annotation.ExplainError;
import com.econovation.recruitcommon.exception.RecruitCodeException;
import com.econovation.recruitcommon.interfaces.SwaggerExampleExceptions;
import com.econovation.recruitdomain.domains.comment.exception.CommentLikeDuplicatedCreateException;
import com.econovation.recruitdomain.domains.comment.exception.CommentLikeNotFoundException;

@ExceptionDoc
public class CommentLikeExceptionDocs implements SwaggerExampleExceptions {
    @ExplainError("댓글 좋아요 조회 실패")
    public RecruitCodeException 댓글_좋아요_없음 = CommentLikeNotFoundException.EXCEPTION;

    @ExplainError("댓글 좋아요 중복 생성")
    public RecruitCodeException 댓글_좋아요_중복_생성 = CommentLikeDuplicatedCreateException.EXCEPTION;
}
