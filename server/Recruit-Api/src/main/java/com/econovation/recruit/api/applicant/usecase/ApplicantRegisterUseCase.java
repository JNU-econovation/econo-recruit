package com.econovation.recruit.api.applicant.usecase;

import com.econovation.recruitcommon.annotation.UseCase;
import com.econovation.recruitdomain.domains.applicant.dto.BlockRequestDto;
import java.util.List;
import java.util.UUID;

@UseCase
public interface ApplicantRegisterUseCase {
    UUID execute(List<BlockRequestDto> blocks);
}
