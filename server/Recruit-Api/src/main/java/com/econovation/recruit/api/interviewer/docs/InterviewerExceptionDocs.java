package com.econovation.recruit.api.interviewer.docs;

import com.econovation.recruitcommon.annotation.ExceptionDoc;
import com.econovation.recruitcommon.annotation.ExplainError;
import com.econovation.recruitcommon.exception.RecruitCodeException;
import com.econovation.recruitcommon.interfaces.SwaggerExampleExceptions;

@ExceptionDoc
public class InterviewerExceptionDocs implements SwaggerExampleExceptions {
    @ExplainError("유효하지 않은 Role 을 입력했을 경우")
    public RecruitCodeException 카드_위치_부적절_오류 = InterviewerInvalidRoleException.EXCEPTION;

    @ExplainError("찾을 수 없는 면접관일 경우")
    public RecruitCodeException 보드_조회_오류 = InterviewerNotFoundException.EXCEPTION;

    @ExplainError("Role 이 한 종류가 아닐 때")
    public RecruitCodeException 보드_위치_부적절_오류 = InterviewerNotOneTypeException.EXCEPTION;

    @ExplainError("로그인이 되지 않은 면접관일 경우")
    public RecruitCodeException 보드_위치_부적절_오류 = InterviewerNotLoginException.EXCEPTION;

    @ExplainError("Idp서버가 정상 동작하지 않을 경우")
    public RecruitCodeException 보드_위치_부적절_오류 = InterviewerIdpServerException.EXCEPTION;
}
