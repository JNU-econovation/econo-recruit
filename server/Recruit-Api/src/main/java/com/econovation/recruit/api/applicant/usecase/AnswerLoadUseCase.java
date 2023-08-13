package com.econovation.recruit.api.applicant.usecase;

import com.econovation.recruitcommon.annotation.UseCase;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@UseCase
public interface AnswerLoadUseCase {
    Map<UUID, Map<String, String>> findAllApplicantVo(List<String> fields);

    Map<UUID, Map<String, String>> findApplicantVoByApplicantId(
            List<String> fields, List<UUID> applicantIds);

    Map<String, String> findApplicantVoByApplicantId(List<String> fields, UUID applicantId);
}
