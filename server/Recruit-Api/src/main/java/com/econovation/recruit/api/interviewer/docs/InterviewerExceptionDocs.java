package com.econovation.recruit.api.interviewer.docs;

import com.econovation.recruitcommon.annotation.ExceptionDoc;
import com.econovation.recruitcommon.annotation.ExplainError;
import com.econovation.recruitcommon.exception.RecruitCodeException;
import com.econovation.recruitcommon.interfaces.SwaggerExampleExceptions;
import com.econovation.recruitdomain.domains.interviewer.exception.InterviewerIdpServerException;
import com.econovation.recruitdomain.domains.interviewer.exception.InterviewerInvalidRoleException;
import com.econovation.recruitdomain.domains.interviewer.exception.InterviewerNotFoundException;
import com.econovation.recruitdomain.domains.interviewer.exception.InterviewerNotLoginException;
import com.econovation.recruitdomain.domains.interviewer.exception.InterviewerNotOneTypeException;

@ExceptionDoc
public class InterviewerExceptionDocs implements SwaggerExampleExceptions {
    @ExplainError("유효하지 않은 Role 을 입력했을 경우")
    public RecruitCodeException 면접관_권한_입력_부적절 = InterviewerInvalidRoleException.EXCEPTION;

    @ExplainError("찾을 수 없는 면접관일 경우")
    public RecruitCodeException 면접관_찾을수_없음 = InterviewerNotFoundException.EXCEPTION;

    @ExplainError("Role 이 한 종류가 아닐 때")
    public RecruitCodeException 면접관_권한_부적절 = InterviewerNotOneTypeException.EXCEPTION;

    @ExplainError("로그인이 되지 않은 면접관일 경우")
    public RecruitCodeException 면접관_로그인_필요 = InterviewerNotLoginException.EXCEPTION;

    @ExplainError("Idp서버가 정상 동작하지 않을 경우")
    public RecruitCodeException 외부_서버_사용_불가 = InterviewerIdpServerException.EXCEPTION;
}
