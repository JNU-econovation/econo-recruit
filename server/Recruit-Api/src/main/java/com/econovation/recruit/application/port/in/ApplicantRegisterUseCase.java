package com.econovation.recruit.application.port.in;


import com.econovation.recruitcommon.annotation.UseCase;
import com.econovation.recruitdomain.domains.applicant.dto.BlockRequestDto;
import java.util.List;

@UseCase
public interface ApplicantRegisterUseCase {
    void execute(List<BlockRequestDto> blocks);
}
