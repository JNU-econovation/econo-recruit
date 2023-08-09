package com.econovation.recruit.api.docs;

import com.econovation.recruitcommon.annotation.ExceptionDoc;
import com.econovation.recruitcommon.annotation.ExplainError;
import com.econovation.recruitcommon.exception.RecruitCodeException;
import com.econovation.recruitcommon.interfaces.SwaggerExampleExceptions;
import com.econovation.recruitdomain.domains.applicant.exception.ApplicantDuplicateSubmitException;

@ExceptionDoc
public class CreateApplicantExceptionDocs implements SwaggerExampleExceptions {
    //    @ExplainError("이벤트 티켓팅 시간이 지났을때.")
    //    public DuDoongCodeException 티켓팅_시간지남 = EventTicketingTimeIsPassedException.EXCEPTION;
    @ExplainError("지원서를 중복으로 제출했을 경우")
    public RecruitCodeException 지원서_중복_제출 = ApplicantDuplicateSubmitException.EXCEPTION;
}
