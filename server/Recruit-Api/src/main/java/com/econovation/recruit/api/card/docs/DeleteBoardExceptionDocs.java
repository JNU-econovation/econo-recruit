package com.econovation.recruit.api.card.docs;

import com.econovation.recruitcommon.annotation.ExceptionDoc;
import com.econovation.recruitcommon.annotation.ExplainError;
import com.econovation.recruitcommon.exception.RecruitCodeException;
import com.econovation.recruitcommon.interfaces.SwaggerExampleExceptions;
import com.econovation.recruitdomain.domains.applicant.exception.ApplicantProhibitDeleteException;

@ExceptionDoc
public class DeleteBoardExceptionDocs implements SwaggerExampleExceptions {
    @ExplainError("지원자 삭제는 금지돼있습니다.")
    public RecruitCodeException 지원자_삭제_금지 = ApplicantProhibitDeleteException.EXCEPTION;
}
