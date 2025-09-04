package com.econovation.recruit.api.email.service;

import com.econovation.recruit.api.applicant.usecase.ApplicantQueryUseCase;
import com.econovation.recruit.api.recruitment.util.LatestRecruitmentVo;
import com.econovation.recruitdomain.common.aop.domainEvent.Events;
import com.econovation.recruitdomain.domains.applicant.domain.MongoAnswer;
import com.econovation.recruitdomain.domains.applicant.domain.state.PassStates;
import com.econovation.recruitdomain.domains.email_template.event.EmailSendEvent;
import com.econovation.recruitinfrastructure.slack.SlackMessageProvider;
import com.econovation.recruitinfrastructure.slack.config.SlackProperties;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
@RequiredArgsConstructor
public class FinalEmailDiscussionEmailScheduler {
    private final ApplicantEmailService emailService;
    private final SlackMessageProvider slackMessageProvider;
    private final SlackProperties slackProperties;
    private final ApplicantQueryUseCase applicantQueryUseCase;
    private final Integer MAX_EMAIL_SEND_RETRY = 3;
    private final LatestRecruitmentVo latestRecruitInfo;

    @Retryable(value = Exception.class, maxAttempts = 3, backoff = @Backoff(delay = 30000))
    @SneakyThrows
    @Async
    @Scheduled(cron = "${econovation.recruit.period.finalDiscussionCron}", zone = "Asia/Seoul")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handle() {
        int year = latestRecruitInfo.getYear();
        int startIndex = 0;
        int batchSize = 14;
        List<MongoAnswer> applicants = getFinalApplicants(year);
        Queue<MongoAnswer> failQueue = new LinkedList<>();
        Map<MongoAnswer, Integer> retryCounts = new HashMap<>(); // Map to track retry counts

        int iteration = 0;
        int limit = 1;
        do {
            List<MongoAnswer> batch = new ArrayList<>(batchSize);
            for (MongoAnswer applicant : applicants) {
                batch.add(applicant);
                if (batch.size() == batchSize) {
                    processBatch(batch, failQueue, retryCounts);
                    batch.clear();
                    TimeUnit.MILLISECONDS.sleep(300);
                }
            }
            if (!batch.isEmpty()) {
                processBatch(batch, failQueue, retryCounts);
                batch.clear();
                TimeUnit.MILLISECONDS.sleep(300);
            }
            if (startIndex >= 10) break;
            startIndex++;
            iteration++;
        } while (iteration < limit);
        failOver(failQueue, retryCounts);
    }

    private void processBatch(
            List<MongoAnswer> batch,
            Queue<MongoAnswer> failQueue,
            Map<MongoAnswer, Integer> retryCounts) {
        for (MongoAnswer applicant : batch) {
            try {
                // 이메일 발송 및 실패 처리
                boolean result = sendEmailWithRetry(applicant, retryCounts, failQueue);
                if (!result) {
                    failQueue.add(applicant);
                    retryCounts.put(applicant, retryCounts.getOrDefault(applicant, 0) + 1);
                }
            } catch (Exception e) {
                log.error(
                        "Email sending failed for: {}", applicant.getQna().get("email").toString());
                failQueue.add(applicant);
                retryCounts.put(applicant, retryCounts.getOrDefault(applicant, 0) + 1);
            }
        }
    }

    private void failOver(Queue<MongoAnswer> failQueue, Map<MongoAnswer, Integer> retryCounts)
            throws InterruptedException {
        while (!failQueue.isEmpty()) {
            int queueSize = failQueue.size();
            for (int i = 0; i < queueSize; i++) {
                MongoAnswer applicant = failQueue.poll();
                int retryCount = retryCounts.getOrDefault(applicant, 0);

                if (retryCount >= MAX_EMAIL_SEND_RETRY) {
                    log.error(
                            "최대 {}번 retry 실패시: {}",
                            MAX_EMAIL_SEND_RETRY,
                            applicant.getQna().get("email").toString());
                    continue;
                }

                try {
                    // 이메일 발송 및 실패 처리
                    boolean result = sendEmailWithRetry(applicant, retryCounts, failQueue);
                    if (!result) {
                        retryCounts.put(applicant, retryCount + 1);
                        failQueue.add(applicant);
                        log.warn(
                                "Retry failed for email: {} (Attempt {})",
                                applicant.getQna().get("email").toString(),
                                retryCount + 1);
                    }
                } catch (Exception e) {
                    log.error(
                            "Retry exception for email {}:",
                            applicant.getQna().get("email").toString());
                    retryCounts.put(applicant, retryCount + 1);
                    failQueue.add(applicant);
                }
            }
            TimeUnit.SECONDS.sleep(1);
        }
    }

    // 이메일 발송 및 실패 처리 메서드
    private boolean sendEmailWithRetry(
            MongoAnswer applicant,
            Map<MongoAnswer, Integer> retryCounts,
            Queue<MongoAnswer> failQueue) {
        boolean result = emailService.sendEmail(applicant);

        if (!result) {
            retryCounts.put(applicant, retryCounts.getOrDefault(applicant, 0) + 1);
            failQueue.add(applicant);
        }

        if (result) {
            String applicantId = applicant.getId();
            String passState = applicant.getApplicantState().getPassStateToEnum().name();

            Events.raise(EmailSendEvent.of(applicantId, passState, ""));
            slackMessageProvider.sendMessage(
                    slackProperties.getUrl(), generateNotificationMessage(applicant));
        }

        return result;
    }

    private List<MongoAnswer> getFinalApplicants(int year) {
        return applicantQueryUseCase.getApplicantsByYear(year).stream()
                .filter(
                        applicant -> {
                            PassStates passState =
                                    applicant.getApplicantState().getPassStateToEnum();
                            return passState == PassStates.FINAL_PASSED
                                    || passState == (PassStates.FINAL_FAILED);
                        })
                .toList();
    }

    private String generateNotificationMessage(MongoAnswer applicant) {
        String message =
                """
                [메일 발송 성공]
                - 이름 : %s
                - 지원 분야 : %s / %s
                - 합격 상태 : %s
                """;

        String name = applicant.getQna().get("name").toString();
        String field = applicant.getQna().get("field").toString();
        String field1 = applicant.getQna().get("field1").toString();
        String field2 = applicant.getQna().get("field2").toString();
        String state = applicant.getApplicantState().getPassStateToEnum().name();

        return String.format(message, name, field1, field2, state);
    }
}
