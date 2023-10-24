package com.econovation.recruit.api.card.docs;

import com.econovation.recruitcommon.annotation.ExceptionDoc;
import com.econovation.recruitcommon.annotation.ExplainError;
import com.econovation.recruitcommon.exception.RecruitCodeException;
import com.econovation.recruitcommon.interfaces.SwaggerExampleExceptions;
import com.econovation.recruitdomain.domains.board.exception.BoardNotFoundException;

@ExceptionDoc
public class FindBoardExceptionDocs implements SwaggerExampleExceptions {
    @ExplainError("보드를 찾을 수 없는 경우")
    public RecruitCodeException 보드_조회_실패 = BoardNotFoundException.EXCEPTION;
}
