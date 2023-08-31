package com.econovation.recruit.api.record.docs;

import com.econovation.recruitcommon.annotation.ExceptionDoc;
import com.econovation.recruitcommon.annotation.ExplainError;
import com.econovation.recruitcommon.exception.RecruitCodeException;
import com.econovation.recruitcommon.interfaces.SwaggerExampleExceptions;
import com.econovation.recruitdomain.domains.record.exception.RecordDuplicateCreatedException;
import com.econovation.recruitdomain.domains.record.exception.RecordNotFoundException;

@ExceptionDoc
public class RecordCreateExceptionDocs implements SwaggerExampleExceptions {
    @ExplainError("면접기록을 중복으로 생성했을 경우")
    public RecruitCodeException 면접기록_중복_생성 = RecordDuplicateCreatedException.EXCEPTION;

    @ExplainError("면접기록을 찾을 수 없을 경우")
    public RecruitCodeException 면접기록_없음 = RecordNotFoundException.EXCEPTION;
}
