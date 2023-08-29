package com.econovation.recruit.api.applicant.usecase;

import com.econovation.recruitcommon.annotation.UseCase;
import java.util.List;
import java.util.Map;

@UseCase
public interface AnswerLoadUseCase {
    List<Map<String, String>> execute(String applicantId);

    Map<String, Map<String, String>> execute(List<String> fields, List<String> applicantIds);

    Map<String, Map<String, String>> execute();

    Map<String, String> execute(List<String> fields, String applicantId);

    Map<String, Map<String, String>> findAllApplicantVo(List<String> fields);
}
