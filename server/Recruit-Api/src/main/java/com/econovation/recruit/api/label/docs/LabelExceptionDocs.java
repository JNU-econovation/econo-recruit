package com.econovation.recruit.api.label.docs;

import com.econovation.recruitcommon.annotation.ExceptionDoc;
import com.econovation.recruitcommon.annotation.ExplainError;
import com.econovation.recruitcommon.exception.RecruitCodeException;
import com.econovation.recruitcommon.interfaces.SwaggerExampleExceptions;
import com.econovation.recruitdomain.domains.label.exception.LabelDuplicateCreatedException;
import com.econovation.recruitdomain.domains.label.exception.LabelNotFoundException;

@ExceptionDoc
public class LabelExceptionDocs implements SwaggerExampleExceptions {
    @ExplainError("라벨을 중복으로 생성했을 경우")
    public RecruitCodeException 라벨_중복_생성 = LabelDuplicateCreatedException.EXCEPTION;

    @ExplainError("라벨을 찾을 수 없을 경우")
    public RecruitCodeException 라벨_없음 = LabelNotFoundException.EXCEPTION;
}
