package com.econovation.recruitdomain.out;


import com.econovation.recruitdomain.domain.applicant.Applicant;
import com.econovation.recruitdomain.domain.score.Score;
import java.util.List;

public interface ScoreLoadPort {
    List<Score> findByApplicant(Applicant applicant);
}
