package com.econovation.recruit.application.port.out;

import com.econovation.recruit.domain.applicant.Applicant;
import com.econovation.recruit.domain.score.Score;

import java.util.List;

public interface ScoreLoadPort {
    List<Score> findByApplicant(Applicant applicant);
}
