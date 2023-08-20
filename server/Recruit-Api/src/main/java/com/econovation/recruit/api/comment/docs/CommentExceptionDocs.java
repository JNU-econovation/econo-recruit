package com.econovation.recruit.api.comment.docs;

import com.econovation.recruitcommon.annotation.ExceptionDoc;
import com.econovation.recruitcommon.annotation.ExplainError;
import com.econovation.recruitcommon.exception.RecruitCodeException;
import com.econovation.recruitcommon.interfaces.SwaggerExampleExceptions;
import com.econovation.recruitdomain.domains.board.exception.BoardInvalidLocationException;
import com.econovation.recruitdomain.domains.board.exception.CardDuplicateCreatedException;
import com.econovation.recruitdomain.domains.comment.exception.CommentNotFoundException;
import com.econovation.recruitdomain.domains.comment.exception.CommentNotHostException;

@ExceptionDoc
public class CommentExceptionDocs implements SwaggerExampleExceptions {
    @ExplainError("댓글을 찾을 수 없는 경우")
    public RecruitCodeException 카드_중복_생성 = CommentNotFoundException.EXCEPTION;

    @ExplainError("댓글을 생성한 사람이 아닐 경우")
    public RecruitCodeException 유효하지_않은_위치_카드_생성 = CommentNotHostException.EXCEPTION;
}