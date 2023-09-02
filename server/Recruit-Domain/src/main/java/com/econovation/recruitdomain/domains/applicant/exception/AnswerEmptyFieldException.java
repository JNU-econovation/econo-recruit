package com.econovation.recruitdomain.domains.applicant.exception;

import com.econovation.recruitcommon.exception.RecruitCodeException;
import com.econovation.recruitdomain.domains.applicant.domain.Answer;

public class AnswerEmptyFieldException extends RecruitCodeException {
    public static RecruitCodeException EXCEPTION = new AnswerEmptyFieldException();
    private AnswerEmptyFieldException() {super(AnswerErrorCode.ANSWER_EMPTY_FIELD);}
}
