package com.econovation.recruit.api.user.docs;

import com.econovation.recruitcommon.annotation.ExceptionDoc;
import com.econovation.recruitcommon.annotation.ExplainError;
import com.econovation.recruitcommon.exception.RecruitCodeException;
import com.econovation.recruitcommon.interfaces.SwaggerExampleExceptions;
import com.econovation.recruitdomain.domains.interviewer.exception.InvalidPasswordException;

@ExceptionDoc
public class SignUpExceptionDocs implements SwaggerExampleExceptions {
    @ExplainError("유효하지 않은 비밀번호를 입력한 경우")
    public RecruitCodeException 비밀번호_부적절 = InvalidPasswordException.EXCEPTION;
}
