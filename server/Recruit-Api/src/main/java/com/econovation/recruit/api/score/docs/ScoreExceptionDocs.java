package com.econovation.recruit.api.score.docs;

import com.econovation.recruitcommon.annotation.ExceptionDoc;
import com.econovation.recruitcommon.annotation.ExplainError;
import com.econovation.recruitcommon.exception.RecruitCodeException;
import com.econovation.recruitcommon.interfaces.SwaggerExampleExceptions;
import com.econovation.recruitdomain.domains.score.exception.ScoreInvalidFieldException;
import com.econovation.recruitdomain.domains.score.exception.ScoreNotFoundException;

@ExceptionDoc
public class ScoreExceptionDocs implements SwaggerExampleExceptions {
    @ExplainError("유효하지 않은 평가 항복에 대한 점수입니다.")
    public RecruitCodeException 카드_중복_생성 = ScoreInvalidFieldException.EXCEPTION;
    @ExplainError("해당 점수를 찾을 수 없습니다.")
    public RecruitCodeException 카드_위치_변경_불가 = ScoreNotFoundException.EXCEPTION;

}
