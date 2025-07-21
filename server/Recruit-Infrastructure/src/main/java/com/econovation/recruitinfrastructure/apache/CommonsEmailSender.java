package com.econovation.recruitinfrastructure.apache;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
@RequiredArgsConstructor
@Slf4j
public class CommonsEmailSender {
    private final CommonsEmailProperties commonsEmailProperties;
    private final TemplateEngine htmlTemplateEngine;

    @Value("${econovation.domain}")
    private String domain;

    public void send(String toEmail, String applicantId, int year, LocalDateTime passedDate) {
        HtmlEmail email = new HtmlEmail();
        email.setCharset("euc-kr");
        email.setHostName(commonsEmailProperties.getHost());
        email.setSmtpPort(465);
        email.setAuthentication(
                commonsEmailProperties.getSenderAddress(), commonsEmailProperties.getPassword());
        email.setSSLOnConnect(true);
        email.setStartTLSEnabled(true);
        try {
            email.addTo(toEmail);
            email.setFrom(
                    commonsEmailProperties.getSenderAddress(),
                    commonsEmailProperties.getSenderName());
            email.setSubject("에코노베이션 지원서 접수 확인");
            email.setHtmlMsg(generateHtml(applicantId, year, passedDate));
            email.send();
        } catch (EmailException e) {
            log.error("Email send error", e);
            throw new IllegalArgumentException(e);
        }
    }

    @Retryable(value = EmailException.class, maxAttempts = 3, backoff = @Backoff(delay = 3000))
    public void send(String toEmail, String subject, String htmlMessage) {
        HtmlEmail email = new HtmlEmail();
        email.setCharset("euc-kr");
        email.setHostName(commonsEmailProperties.getHost());
        email.setSmtpPort(465);
        email.setAuthentication(
                commonsEmailProperties.getSenderAddress(), commonsEmailProperties.getPassword());
        email.setSSLOnConnect(true);
        email.setStartTLSEnabled(true);
        try {
            email.addTo(toEmail);
            email.setFrom(
                    commonsEmailProperties.getSenderAddress(),
                    commonsEmailProperties.getSenderName());
            email.setSubject(subject);
            email.setHtmlMsg(htmlMessage);
            email.send();
        } catch (EmailException e) {
            log.error("Email send error", e);
            throw new IllegalArgumentException(e);
        }
    }

    @Retryable(value = EmailException.class, maxAttempts = 3, backoff = @Backoff(delay = 3000))
    public boolean sendEmail(String toEmail, String subject, String htmlMessage) {
        HtmlEmail email = new HtmlEmail();
        email.setCharset("euc-kr");
        email.setHostName(commonsEmailProperties.getHost());
        email.setSmtpPort(465);
        email.setAuthentication(
                commonsEmailProperties.getSenderAddress(), commonsEmailProperties.getPassword());
        email.setSSLOnConnect(true);
        email.setStartTLSEnabled(true);

        try {
            email.addTo(toEmail);
            email.setFrom(
                    commonsEmailProperties.getSenderAddress(),
                    commonsEmailProperties.getSenderName());
            email.setSubject(subject);
            email.setHtmlMsg(htmlMessage);
            email.send();
            return true; // 이메일 발송 성공 시 true 반환
        } catch (EmailException e) {
            log.error("Email send error", e);
            return false; // 예외 발생 시 false 반환
        }
    }

    @Retryable(value = EmailException.class, maxAttempts = 3, backoff = @Backoff(delay = 3000))
    public boolean sendEmailWithAttachment(
            String toEmail, String subject, String htmlMessage, File attachment) {
        HtmlEmail email = new HtmlEmail();
        email.setCharset("euc-kr");
        email.setHostName(commonsEmailProperties.getHost());
        email.setSmtpPort(465);
        email.setAuthentication(
                commonsEmailProperties.getSenderAddress(), commonsEmailProperties.getPassword());
        email.setSSLOnConnect(true);
        email.setStartTLSEnabled(true);

        try {
            email.addTo(toEmail);
            email.setFrom(
                    commonsEmailProperties.getSenderAddress(),
                    commonsEmailProperties.getSenderName());
            email.setSubject(subject);
            email.setHtmlMsg(htmlMessage);
            email.attach(attachment);
            email.send();
            return true; // 이메일 발송 성공 시 true 반환
        } catch (EmailException e) {
            log.error("Email send error", e);
            return false; // 예외 발생 시 false 반환
        }
    }

    public String generateHtml(String applicantId, Integer year, LocalDateTime passedDate) {
        Context context = new Context();
        context.setVariable("year", year.toString());
        context.setVariable("domain", domain);
        context.setVariable(
                "passedDate",
                passedDate.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 / HH:mm:ss")));
        context.setVariable("applicantId", applicantId);
        return htmlTemplateEngine.process("email-register-confirm", context);
    }
}
