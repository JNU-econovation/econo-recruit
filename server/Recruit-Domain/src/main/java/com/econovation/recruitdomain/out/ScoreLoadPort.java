package com.econovation.recruitdomain.out;

import com.econovation.recruitdomain.domains.score.domain.Score;
import java.util.List;

public interface ScoreLoadPort {
    List<Score> findByApplicantId(String applicantId);

    List<Score> findByApplicantIds(List<String> applicantIds);
}
