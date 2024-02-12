package com.econovation.recruit.api.applicant.docs;

import com.econovation.recruitcommon.annotation.ExceptionDoc;
import com.econovation.recruitcommon.annotation.ExplainError;
import com.econovation.recruitcommon.exception.RecruitCodeException;
import com.econovation.recruitcommon.interfaces.SwaggerExampleExceptions;
import com.econovation.recruitdomain.domains.applicant.exception.ApplicantDuplicateSubmitException;
import com.econovation.recruitdomain.domains.applicant.exception.ApplicantWrongPositionException;

@ExceptionDoc
public class CreateApplicantExceptionDocs implements SwaggerExampleExceptions {
    @ExplainError("지원서를 중복으로 제출했을 경우")
    public RecruitCodeException 지원서_중복_제출 = ApplicantDuplicateSubmitException.EXCEPTION;

    @ExplainError("지원서를 잘못된 포지션으로 제출했을 경우")
    public RecruitCodeException 지원서_지원_포지션_오류 = ApplicantWrongPositionException.EXCEPTION;

    @ExplainError("지원서를 중복으로 제출했을 경우")
    public RecruitCodeException 지원서_중복_제출_예외 = ApplicantDuplicateSubmitException.EXCEPTION;
}
