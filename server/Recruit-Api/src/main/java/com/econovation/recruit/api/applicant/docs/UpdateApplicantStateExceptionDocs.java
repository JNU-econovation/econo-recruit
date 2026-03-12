package com.econovation.recruit.api.applicant.docs;

import com.econovation.recruitcommon.annotation.ExceptionDoc;
import com.econovation.recruitcommon.annotation.ExplainError;
import com.econovation.recruitcommon.exception.RecruitCodeException;
import com.econovation.recruitcommon.interfaces.SwaggerExampleExceptions;
import com.econovation.recruitdomain.domains.applicant.exception.ApplicantNotFoundException;
import com.econovation.recruitdomain.domains.applicant.exception.NotOperatedException;

@ExceptionDoc
public class UpdateApplicantStateExceptionDocs implements SwaggerExampleExceptions {
    @ExplainError("현재 기간에서 불가능한 합/불 상태로 전이하려 할 경우")
    public RecruitCodeException 상태_변경_불가 = NotOperatedException.EXCEPTION;

    @ExplainError("지원자를 찾을 수 없을 경우")
    public RecruitCodeException 지원자_없음 = ApplicantNotFoundException.EXCEPTION;
}
