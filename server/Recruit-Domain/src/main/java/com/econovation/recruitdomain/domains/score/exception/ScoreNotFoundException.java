package com.econovation.recruitdomain.domains.score.exception;

import com.econovation.recruitcommon.exception.RecruitCodeException;

public class ScoreNotFoundException extends RecruitCodeException {
    public static final ScoreNotFoundException EXCEPTION = new ScoreNotFoundException();

    private ScoreNotFoundException() {
        super(ScoreErrorCode.SCORE_INVALID_FIELD_SUBMIT);
    }
}
