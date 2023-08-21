package com.econovation.recruitdomain.domains.score.exception;

import com.econovation.recruitcommon.exception.RecruitCodeException;

public class ScoreInvalidFieldException extends RecruitCodeException {
    public static final ScoreInvalidFieldException EXCEPTION = new ScoreInvalidFieldException();

    private ScoreInvalidFieldException() {
        super(ScoreErrorCode.SCORE_INVALID_FIELD_SUBMIT);
    }
}
