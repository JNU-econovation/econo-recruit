package com.econovation.recruit.api.card.docs;

import com.econovation.recruitcommon.annotation.ExceptionDoc;
import com.econovation.recruitcommon.annotation.ExplainError;
import com.econovation.recruitcommon.exception.RecruitCodeException;
import com.econovation.recruitcommon.interfaces.SwaggerExampleExceptions;
import com.econovation.recruitdomain.domains.board.exception.NavigationNotFoundException;

@ExceptionDoc
public class FindNavigationExceptionDocs implements SwaggerExampleExceptions {
    @ExplainError("Navigation을 찾을 수 없는 경우")
    public RecruitCodeException Navigation_카드_없음 = NavigationNotFoundException.EXCEPTION;
}
