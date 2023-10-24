package com.econovation.recruit.utils.aop;

import com.econovation.recruitdomain.domains.applicant.dto.BlockRequestDto;
import com.econovation.recruitinfrastructure.slack.SlackMessageProvider;
import com.econovation.recruitinfrastructure.slack.config.SlackProperties;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class XssValidationAspect {
    private final SlackMessageProvider slackMessageProvider;
    private final SlackProperties slackProperties;

    @Around("@annotation(com.econovation.recruitcommon.annotation.XssProtected)")
    public Object validateXssInput(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof List) {
                validateDtoList((List<?>) arg);
            }
        }
        return joinPoint.proceed();
    }

    private Boolean validateDtoList(List<?> dtoList) {
        for (Object dto : dtoList) {
            if (dto instanceof BlockRequestDto) {
                Boolean attackSignal = validateBlockRequestDto((BlockRequestDto) dto);
                if (attackSignal) {
                    return true;
                }
            }
        }
        return false;
    }

    private Boolean validateBlockRequestDto(BlockRequestDto dto) {
        String sanitizedAnswer = Jsoup.clean(dto.getAnswer(), Whitelist.relaxed());
        if (!dto.getAnswer().equals(sanitizedAnswer)) {
            slackMessageProvider.sendMessage(
                    slackProperties.getUrl(), generateApplicantRegisterMessage(dto));
            return true;
        }
        return false;
    }

    private String generateApplicantRegisterMessage(BlockRequestDto dto) {
        String inputAnswer = dto.getAnswer();
        String sanitizedAnswer = Jsoup.clean(inputAnswer, Whitelist.relaxed());

        if (!inputAnswer.equals(sanitizedAnswer)) {
            // XSS 공격이 의심되는 부분을 추출합니다.
            String detectedXss = extractDetectedXss(inputAnswer, sanitizedAnswer);

            // 경고 메시지 생성
            return String.format(
                    ":interrobang::interrobang::interrobang: XSS 공격이 감지되었습니다.:interrobang::interrobang::interrobang:\n\n"
                            + ":party_parrot2: 입력값 : %s\n\n"
                            + ":party_parrot2: 변환된 공격 추출 값 : %s",
                    inputAnswer, detectedXss);
        }

        // 공격이 감지되지 않은 경우
        return "No XSS attacks detected.";
    }

    private String extractDetectedXss(String inputAnswer, String sanitizedAnswer) {
        // inputAnswer와 sanitizedAnswer를 비교하여 공격이 감지된 부분을 추출합니다.
        StringBuilder detectedXss = new StringBuilder();

        for (int i = 0; i < inputAnswer.length(); i++) {
            if (i >= sanitizedAnswer.length()
                    || inputAnswer.charAt(i) != sanitizedAnswer.charAt(i)) {
                detectedXss.append(inputAnswer.charAt(i));
            }
        }

        return detectedXss.toString();
    }
}
