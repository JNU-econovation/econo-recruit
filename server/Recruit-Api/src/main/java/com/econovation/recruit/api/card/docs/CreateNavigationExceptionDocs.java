package com.econovation.recruit.api.card.docs;

import com.econovation.recruitcommon.annotation.ExceptionDoc;
import com.econovation.recruitcommon.annotation.ExplainError;
import com.econovation.recruitcommon.exception.RecruitCodeException;
import com.econovation.recruitcommon.interfaces.SwaggerExampleExceptions;
import com.econovation.recruitdomain.domains.board.exception.NavigationDuplicateCreatedException;

@ExceptionDoc
public class CreateNavigationExceptionDocs implements SwaggerExampleExceptions {
    @ExplainError("중복된 navigation를 생성했을 경우")
    public RecruitCodeException navigation_중복_생성 = NavigationDuplicateCreatedException.EXCEPTION;
}