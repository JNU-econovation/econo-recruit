package com.econovation.recruit.api.applicant.usecase;

import com.econovation.recruitcommon.annotation.UseCase;
import java.util.Map;
import javax.validation.Valid;

@UseCase
public interface ApplicantMongoRegisterUseCase {
    void execute(Map<String, Object> blocks);

}
