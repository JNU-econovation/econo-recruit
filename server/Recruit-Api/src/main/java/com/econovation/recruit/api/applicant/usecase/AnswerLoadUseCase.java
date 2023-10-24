package com.econovation.recruit.api.applicant.usecase;

import com.econovation.recruitcommon.annotation.UseCase;
import com.econovation.recruitdomain.domains.dto.ApplicantPaginationResponseDto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@UseCase
public interface AnswerLoadUseCase {
    Map<String, String> execute(String applicantId);

    List<Map<String, String>> execute(List<String> fields, List<String> applicantIds);

    List<Map<String, String>> execute();

    ApplicantPaginationResponseDto execute(Integer page);
    //    List<Map<String, String>> execute(Integer page, String sortType);

    Map<String, String> execute(String applicantId, List<String> fields);

    List<Map<String, String>> execute(List<String> fields);

    Map<String, HashMap<String, String>> findAllApplicantVo(List<String> fields);
}
