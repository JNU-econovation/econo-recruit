package com.econovation.recruit.api.user.docs;

import com.econovation.recruitcommon.annotation.ExceptionDoc;
import com.econovation.recruitcommon.annotation.ExplainError;
import com.econovation.recruitcommon.exception.RecruitCodeException;
import com.econovation.recruitcommon.interfaces.SwaggerExampleExceptions;
import com.econovation.recruitdomain.domains.interviewer.exception.InterviewerNotMatchException;
import com.econovation.recruitdomain.domains.interviewer.exception.InvalidPasswordException;

@ExceptionDoc
public class LoginExceptionDocs implements SwaggerExampleExceptions {
    @ExplainError("유효하지 않은 비밀번호를 입력한 경우")
    public RecruitCodeException 비밀번호_부적절 = InvalidPasswordException.EXCEPTION;

    @ExplainError("등록되지 않은 이메일과 비밀번호로 로그인을 시도한 경우")
    public RecruitCodeException 이메일_비밀번호_불일치 = InterviewerNotMatchException.EXCEPTION;
}
