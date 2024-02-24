package com.econovation.recruit.api.interviewer.docs;

import com.econovation.recruitcommon.annotation.ExceptionDoc;
import com.econovation.recruitcommon.annotation.ExplainError;
import com.econovation.recruitcommon.exception.RecruitCodeException;
import com.econovation.recruitcommon.interfaces.SwaggerExampleExceptions;
import com.econovation.recruitdomain.domains.interviewer.exception.InterviewerNotFoundException;

@ExceptionDoc
public class InterviewerDeleteExceptionDocs implements SwaggerExampleExceptions {
    @ExplainError("찾을 수 없는 면접관일 경우")
    public RecruitCodeException 면접관_찾을수_없음 = InterviewerNotFoundException.EXCEPTION;
}
