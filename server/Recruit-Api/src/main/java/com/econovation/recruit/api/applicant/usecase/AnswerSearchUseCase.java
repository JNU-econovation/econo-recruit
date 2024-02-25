package com.econovation.recruit.api.applicant.usecase;

import com.econovation.recruitcommon.annotation.UseCase;
import com.econovation.recruitdomain.domains.dto.ApplicantPaginationResponseDto;
import java.util.List;
import java.util.Map;

@UseCase
public interface AnswerSearchUseCase {
    List<ApplicantPaginationResponseDto> searchApplicants(String keyword, int page, int size);

    Map<String, Object> searchApplicants(
            String keyword, int page, int size, String sort, String order);
}
