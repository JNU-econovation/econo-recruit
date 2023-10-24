package com.econovation.recruit.api.card.docs;

import com.econovation.recruitcommon.annotation.ExceptionDoc;
import com.econovation.recruitcommon.annotation.ExplainError;
import com.econovation.recruitcommon.exception.RecruitCodeException;
import com.econovation.recruitcommon.interfaces.SwaggerExampleExceptions;
import com.econovation.recruitdomain.domains.board.exception.BoardInvalidLocationException;
import com.econovation.recruitdomain.domains.board.exception.BoardNotFoundException;

@ExceptionDoc
public class UpdateBoardExceptionDocs implements SwaggerExampleExceptions {
    @ExplainError("유효하지 않은 위치로 카드를 이동했을 경우")
    public RecruitCodeException 카드_위치_부적절_오류 = BoardInvalidLocationException.EXCEPTION;

    @ExplainError("찾을 수 없는 보드를 조회했을 경우")
    public RecruitCodeException 보드_조회_오류 = BoardNotFoundException.EXCEPTION;
}
