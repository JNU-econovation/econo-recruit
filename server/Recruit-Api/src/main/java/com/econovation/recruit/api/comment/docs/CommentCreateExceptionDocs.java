package com.econovation.recruit.api.comment.docs;

import com.econovation.recruitcommon.annotation.ExceptionDoc;
import com.econovation.recruitcommon.annotation.ExplainError;
import com.econovation.recruitcommon.exception.RecruitCodeException;
import com.econovation.recruitcommon.interfaces.SwaggerExampleExceptions;
import com.econovation.recruitdomain.domains.comment.exception.CommentInvalidCreatedException;
import com.econovation.recruitdomain.domains.comment.exception.CommentNotApplicantException;
import com.econovation.recruitdomain.domains.comment.exception.CommentNotHostException;

@ExceptionDoc
public class CommentCreateExceptionDocs implements SwaggerExampleExceptions {
    @ExplainError("댓글을 생성한 사람이 아닐 경우")
    public RecruitCodeException 댓글_생성한_사람이_아닌경우 = CommentNotHostException.EXCEPTION;

    @ExplainError("댓글이 지원서 댓글이 아닌 경우")
    public RecruitCodeException 지원서_댓글_아님 = CommentNotApplicantException.EXCEPTION;

    @ExplainError("댓글을 샛성할 수 없는 경우")
    public RecruitCodeException 댓글_생성_불가 = CommentInvalidCreatedException.EXCEPTION;
}
