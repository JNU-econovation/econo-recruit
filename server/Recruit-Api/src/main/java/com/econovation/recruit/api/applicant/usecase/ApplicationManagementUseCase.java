package com.econovation.recruit.api.applicant.usecase;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface ApplicationManagementUseCase {

    boolean applicationStart(LocalDateTime startAt);

}
