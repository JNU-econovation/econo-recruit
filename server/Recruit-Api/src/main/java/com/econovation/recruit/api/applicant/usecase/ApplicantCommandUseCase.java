package com.econovation.recruit.api.applicant.usecase;

import com.econovation.recruitcommon.annotation.UseCase;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@UseCase
public interface ApplicantCommandUseCase {
    UUID execute(Map<String, Object> blocks);

    String execute(String applicantId, String state);

    UUID execute(Map<String, Object> blocks, UUID id);

    void deleteByYear(Integer year);

    void deleteByApplicantIds(List<String> applicantIds);
}
