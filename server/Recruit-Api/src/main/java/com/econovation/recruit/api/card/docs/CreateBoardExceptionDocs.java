package com.econovation.recruit.api.card.docs;

import com.econovation.recruitcommon.annotation.ExceptionDoc;
import com.econovation.recruitcommon.annotation.ExplainError;
import com.econovation.recruitcommon.exception.RecruitCodeException;
import com.econovation.recruitcommon.interfaces.SwaggerExampleExceptions;
import com.econovation.recruitdomain.domains.board.exception.BoardInvalidLocationException;
import com.econovation.recruitdomain.domains.board.exception.CardDuplicateCreatedException;

@ExceptionDoc
public class CreateBoardExceptionDocs implements SwaggerExampleExceptions {
    @ExplainError("중복된 카드를 생성했을 경우")
    public RecruitCodeException 카드_중복_생성 = CardDuplicateCreatedException.EXCEPTION;

    @ExplainError("유효하지 않은 위치에 카드를 생성했을 경우")
    public RecruitCodeException 유효하지_않은_위치_카드_생성 = BoardInvalidLocationException.EXCEPTION;

    @ExplainError("유효하지 않은 위치로 카드를 이동했을 경우")
    public RecruitCodeException 유효하지_않은_위치_카드_이동 = BoardInvalidLocationException.EXCEPTION;
}
