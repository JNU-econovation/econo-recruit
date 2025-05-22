package com.econovation.recruit.api.email.service;

import com.econovation.recruit.api.applicant.usecase.ApplicantQueryUseCase;
import com.econovation.recruitdomain.domains.applicant.domain.MongoAnswer;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicantEmailReserveService {

    private final ApplicantQueryUseCase applicantQueryUseCase;
    private final ApplicantEmailService emailService; // 이게 맞나..
    private final TaskScheduler taskScheduler;

    public void reserve(Set<String> applicantIds, LocalDateTime reservedAt) {
        List<MongoAnswer> applicants =
                applicantQueryUseCase.execute(applicantIds.stream().toList());
        Set<String> emails =
                applicants.stream()
                        .map(applicant -> (String) applicant.getQna().getOrDefault("email", ""))
                        .collect(Collectors.toSet());

        ZonedDateTime zoned = reservedAt.atZone(ZoneId.of("Asia/Seoul"));

        taskScheduler.schedule(() -> emails.forEach(emailService::sendEmail), zoned.toInstant());
    }
}
