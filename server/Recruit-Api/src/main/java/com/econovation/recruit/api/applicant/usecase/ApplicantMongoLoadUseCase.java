package com.econovation.recruit.api.applicant.usecase;

import com.econovation.recruitcommon.annotation.UseCase;
import java.util.Map;

@UseCase
public interface ApplicantMongoLoadUseCase {
    Map<String, Object> execute(String applicantId);
}
