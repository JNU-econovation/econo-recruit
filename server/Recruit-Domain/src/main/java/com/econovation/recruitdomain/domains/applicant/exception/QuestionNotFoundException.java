package com.econovation.recruitdomain.domains.applicant.exception;

import com.econovation.recruitcommon.exception.RecruitCodeException;

public class QuestionNotFoundException extends RecruitCodeException {
    public static RecruitCodeException EXCEPTION = new QuestionNotFoundException();

    private QuestionNotFoundException() {
        super(QuestionErrorCode.QUESTION_NOT_FOUND);
    }
}
