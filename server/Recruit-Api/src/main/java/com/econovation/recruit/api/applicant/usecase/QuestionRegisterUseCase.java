package com.econovation.recruit.api.applicant.usecase;

import com.econovation.recruitcommon.annotation.UseCase;
import com.econovation.recruitdomain.domains.dto.QuestionRequestDto;
import java.util.List;

@UseCase
public interface QuestionRegisterUseCase {
    void execute(String type, String key, Integer parentId);

    void execute(List<QuestionRequestDto> questions);
}
