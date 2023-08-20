package com.econovation.recruitdomain.out;

import com.econovation.recruitdomain.domains.score.Score;
import java.util.List;
import java.util.UUID;

public interface ScoreLoadPort {
    List<Score> findByApplicantId(UUID applicantId);
}
