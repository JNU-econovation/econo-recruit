package com.econovation.recruit.api.applicant.service;

import com.econovation.recruit.api.applicant.usecase.ApplicationManagementUseCase;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationManageService implements ApplicationManagementUseCase {

    @Override
    public boolean applicationStart(LocalDateTime startAt) {
        return false;
    }
}
