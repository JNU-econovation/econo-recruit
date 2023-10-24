package com.econovation.recruit.api.card.docs;

import com.econovation.recruitcommon.annotation.ExceptionDoc;
import com.econovation.recruitcommon.annotation.ExplainError;
import com.econovation.recruitcommon.exception.RecruitCodeException;
import com.econovation.recruitcommon.interfaces.SwaggerExampleExceptions;
import com.econovation.recruitdomain.domains.board.exception.ColumnsDuplicateCreatedException;
import com.econovation.recruitdomain.domains.board.exception.ColumnsNotFoundException;

@ExceptionDoc
public class CreateColumnsExceptionDocs implements SwaggerExampleExceptions {
    @ExplainError("칼럼을 찾을 수 없을 경우")
    public RecruitCodeException 칼럼_조회_오류 = ColumnsNotFoundException.EXCEPTION;

    @ExplainError("중복된 칼럼을 생성했을 경우")
    public RecruitCodeException 칼럼_중복_생성 = ColumnsDuplicateCreatedException.EXCEPTION;
}
