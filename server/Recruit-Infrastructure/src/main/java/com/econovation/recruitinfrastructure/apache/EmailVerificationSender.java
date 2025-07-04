package com.econovation.recruitinfrastructure.apache;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmailVerificationSender {
    private final CommonsEmailSender commonsEmailSender;
    private final TemplateEngine htmlTemplateEngine;

    public void sendVerificationCode(String toEmail, String code) {
        String subject = "에코노베이션 이메일 인증 코드";
        String html = generateHtml(code);
        commonsEmailSender.send(toEmail, subject, html);
    }

    private String generateHtml(String code) {
        Context context = new Context();
        context.setVariable("code", code);
        return htmlTemplateEngine.process("email-verification", context);
    }
}
