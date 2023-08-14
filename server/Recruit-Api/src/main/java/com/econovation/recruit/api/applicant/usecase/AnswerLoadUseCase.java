package com.econovation.recruit.api.applicant.usecase;

import com.econovation.recruitcommon.annotation.UseCase;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@UseCase
public interface AnswerLoadUseCase {
    List<Map<String, String>> execute(UUID applicantId);

    Map<UUID, Map<String, String>> execute(List<String> fields, List<UUID> applicantIds);

    Map<UUID, Map<String, String>> execute();

    Map<String, String> execute(List<String> fields, UUID applicantId);

    Map<UUID, Map<String, String>> findAllApplicantVo(List<String> fields);
}
