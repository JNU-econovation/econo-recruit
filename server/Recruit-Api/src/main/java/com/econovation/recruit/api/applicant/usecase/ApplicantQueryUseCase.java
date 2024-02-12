package com.econovation.recruit.api.applicant.usecase;

import com.econovation.recruit.api.applicant.dto.AnswersResponseDto;
import com.econovation.recruitcommon.annotation.UseCase;
import java.util.List;
import java.util.Map;

@UseCase
public interface ApplicantQueryUseCase {
    // clear
    Map<String, Object> execute(String applicantId);

    AnswersResponseDto execute(Integer year, Integer page);
    //    List<Map<String, String>> execute(List<String> fields, List<String> applicantIds);

    List<Map<String, Object>> execute();

    //    ApplicantPaginationResponseDto execute(Integer page);

    //    List<Map<String, String>> execute(Integer page, String sortType);

    Map<String, Object> execute(String applicantId, List<String> fields);

    List<Map<String, Object>> execute(List<String> fields, Integer page);

    List<Map<String, Object>> execute(List<String> fields, Integer year, Integer page);

    Map<String, Map<String, Object>> findAllApplicantVo(List<String> fields);
}
