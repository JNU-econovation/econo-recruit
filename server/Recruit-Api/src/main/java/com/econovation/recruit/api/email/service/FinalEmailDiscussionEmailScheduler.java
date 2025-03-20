package com.econovation.recruit.api.email.service;

import com.econovation.recruit.api.applicant.usecase.ApplicantQueryUseCase;
import com.econovation.recruitdomain.domains.applicant.domain.MongoAnswer;
import com.econovation.recruitdomain.domains.applicant.domain.state.PassStates;
import com.econovation.recruitinfrastructure.apache.CommonsEmailSender;
import com.econovation.recruitinfrastructure.slack.SlackMessageProvider;
import com.econovation.recruitinfrastructure.slack.config.SlackProperties;
import com.econovation.recruitinfrastructure.slack.config.SlackTFProperties;
import java.io.File;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
@Slf4j
@RequiredArgsConstructor
public class FinalEmailDiscussionEmailScheduler {
    private final CommonsEmailSender emailSender;
    private final TemplateEngine templateEngine;
    private final SlackMessageProvider slackMessageProvider;
    private final SlackProperties slackProperties;
    private final ApplicantQueryUseCase applicantQueryUseCase;
    private final Integer MAX_EMAIL_SEND_RETRY = 10;

    @Value("${econovation.year}")
    private Integer year;

    private File attachment;

    @Value("${econovation.file.path.portfolio}")
    private String filePath;

    private String openChatUrl;

    private LocalDateTime urlDeadLine;

    @Retryable(value = Exception.class, maxAttempts = 3, backoff = @Backoff(delay = 30000))
    @SneakyThrows
    @Async
    @Scheduled(cron = "${econovation.recruit.period.finalDiscussionCron}", zone = "Asia/Seoul")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handle() {
        int startIndex = 0;
        int batchSize = 14;
        List<MongoAnswer> applicants = applicantQueryUseCase.getApplicantsByYear(year);
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

    // 포트폴리오 파일을 가져오는 메서드
    private File getPortfolioFile() {
        // applicant에서 포트폴리오 파일 경로나 ID 등을 이용해 파일을 가져오는 로직
        return new File(filePath);
    }

    private void processBatch(
            List<MongoAnswer> batch,
            Queue<MongoAnswer> failQueue,
            Map<MongoAnswer, Integer> retryCounts) {
        for (MongoAnswer applicant : batch) {
            try {
                // 이메일 템플릿 생성
                String template = generateEmailTemplate(applicant);
                attachment = getPortfolioFile();

                // 이메일 발송 및 실패 처리
                boolean result =
                        sendEmailWithRetry(applicant, template, attachment, retryCounts, failQueue);
                if (!result) {
                    failQueue.add(applicant);
                    retryCounts.put(applicant, retryCounts.getOrDefault(applicant, 0) + 1);
                }
            } catch (Exception e) {
                log.error(
                        "Email sending failed for {}: {}",
                        applicant.getQna().get("email").toString(),
                        e.getMessage());
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
                    log.error("최대 10번 retry 실패시: {}", applicant.getQna().get("email").toString());
                    continue;
                }

                try {
                    // 이메일 템플릿 생성
                    String template = generateEmailTemplate(applicant);

                    // 이메일 발송 및 실패 처리
                    boolean result =
                            sendEmailWithRetry(
                                    applicant, template, attachment, retryCounts, failQueue);
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
                            "Retry exception for email {}: {}",
                            applicant.getQna().get("email").toString(),
                            e.getMessage());
                    retryCounts.put(applicant, retryCount + 1);
                    failQueue.add(applicant);
                }
            }
            TimeUnit.SECONDS.sleep(1);
        }
    }

    // 이메일 템플릿 생성 메서드
    private String generateEmailTemplate(MongoAnswer applicant) {
        PassStates passState = applicant.getApplicantState().getPassStateToEnum();
        String template = "";
        switch (passState) {
            case FINAL_PASSED:
                template = generateFinalPassedTemplate(applicant);
                break;
            case FINAL_FAILED:
                template = generateFinalFailedTemplate(applicant);
                break;
            case FIRST_FAILED:
                template = generateFirstFailedTemplate(applicant);
                break;
            case FIRST_PASSED:
                template = generateFirstPassedTemplate(applicant);
                break;
            default:
                log.error("잘못된 상태 처리: {}", applicant.getId());
        }
        return template;
    }

    // 이메일 발송 및 실패 처리 메서드
    private boolean sendEmailWithRetry(
            MongoAnswer applicant,
            String template,
            File attachment,
            Map<MongoAnswer, Integer> retryCounts,
            Queue<MongoAnswer> failQueue) {
        boolean result;
        if (attachment.exists()) {
            result =
                    emailSender.sendEmailWithAttachment(
                            applicant.getQna().get("email").toString(),
                            "에코노베이션 신입 모집 최종 결과 안내",
                            template,
                            attachment);
        } else {
            result =
                    emailSender.sendEmail(
                            applicant.getQna().get("email").toString(),
                            "에코노베이션 신입 모집 최종 결과 안내",
                            template);
            log.error("attachment 가 첨부되지 않았습니다. file dir : " + attachment.getAbsolutePath());
        }

        if (!result) {
            retryCounts.put(applicant, retryCounts.getOrDefault(applicant, 0) + 1);
            failQueue.add(applicant);
        }

        if(result) {
            slackMessageProvider.sendMessage(slackProperties.getUrl(), generateNotificationMessage(applicant));
        }

        return result;
    }

    private String generateNotificationMessage(MongoAnswer applicant) {
        String message = """
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

    /** 면접 합격자 이메일 템플릿 */
    private String generateFinalPassedTemplate(MongoAnswer applicant) {
        Context context = new Context();
        context.setVariable("name", applicant.getQna().get("name").toString());
        context.setVariable("year", applicant.getYear());

        return templateEngine.process("email-final-passed", context);
    }

    /** 면접 탈락자 이메일 템플릿 ) */
    private String generateFinalFailedTemplate(MongoAnswer applicant) {
        Context context = new Context();
        context.setVariable("name", applicant.getQna().get("name").toString());
        context.setVariable("year", applicant.getYear());

        return templateEngine.process("email-final-failed", context);
    }

    /** 서류 합격자 이메일 템플릿 */
    private String generateFirstPassedTemplate(MongoAnswer applicant) {
        Context context = new Context();
        context.setVariable("name", applicant.getQna().get("name").toString());
        context.setVariable("year", applicant.getYear());
        context.setVariable("deadline", urlDeadLine);
        context.setVariable("link", openChatUrl);

        return templateEngine.process("email-first-passed", context);
    }

    /** 서류 탈락자 이메일 템플릿 */
    private String generateFirstFailedTemplate(MongoAnswer applicant) {
        Context context = new Context();
        context.setVariable("name", applicant.getQna().get("name").toString());
        context.setVariable("year", applicant.getYear());

        return templateEngine.process("email-first-failed", context);
    }
}
