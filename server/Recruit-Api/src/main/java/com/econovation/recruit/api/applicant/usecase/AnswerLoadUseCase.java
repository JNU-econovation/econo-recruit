package com.econovation.recruit.api.applicant.usecase;

import com.econovation.recruitcommon.annotation.UseCase;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@UseCase
public interface AnswerLoadUseCase {
    Map<String, String> execute(String applicantId);

    List<Map<String, String>> execute(List<String> fields, List<String> applicantIds);

    List<Map<String, String>> execute();

    Map<String, String> execute(List<String> fields, String applicantId);

    Map<String, HashMap<String, String>> findAllApplicantVo(List<String> fields);
}
