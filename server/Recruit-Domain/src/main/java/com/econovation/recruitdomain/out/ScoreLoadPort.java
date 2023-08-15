package com.econovation.recruitdomain.out;

import com.econovation.recruitdomain.domains.score.Score;
import java.util.List;

public interface ScoreLoadPort {
    List<Score> findByApplicantId(Integer applicantId);
}
