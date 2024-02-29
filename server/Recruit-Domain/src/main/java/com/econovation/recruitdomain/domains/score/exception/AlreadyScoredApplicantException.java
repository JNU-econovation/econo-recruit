package com.econovation.recruitdomain.domains.score.exception;

import com.econovation.recruitcommon.exception.RecruitCodeException;

public class AlreadyScoredApplicantException extends RecruitCodeException {
    public static final AlreadyScoredApplicantException EXCEPTION =
            new AlreadyScoredApplicantException();

    private AlreadyScoredApplicantException() {
        super(ScoreErrorCode.ALREADY_SCORED_APPLICANT);
    }
}
