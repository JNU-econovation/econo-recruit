package com.econovation.recruitinfrastructure.apache;

import lombok.RequiredArgsConstructor;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommonsEmailSender {
    private final CommonsEmailProperties commonsEmailProperties;

    @Value("${econovation.year}")
    private Integer year;

    public void send(String toEmail, String name, String contents) {
        SimpleEmail email = new SimpleEmail();
        email.setCharset("euc-kr");
        email.setHostName(commonsEmailProperties.getHost());
        email.setSmtpPort(465);
        email.setAuthentication(
                commonsEmailProperties.getSenderAddress(), commonsEmailProperties.getPassword());
        email.setSSLOnConnect(true);
        email.setStartTLSEnabled(true);
        try {
            email.addTo(toEmail, name);
            email.setFrom(
                    commonsEmailProperties.getSenderAddress(),
                    commonsEmailProperties.getSenderName());
            email.setSubject("에코노베이션 지원서 접수 확인 메일");
            email.setContent(contents, "text/html; charset=euc-kr");
            email.send();
        } catch (EmailException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void send(String toEmail, String applicantId) {
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
            email.setHtmlMsg(generateHtml(applicantId));
            email.send();
        } catch (EmailException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private String generateHtml(String applicantId) {
        String html =
                "<!DOCTYPE html><html lang=\"ko\"><head> <meta charset=\"utf-8\"> <meta name=\"viewport\" content=\"width=device-width,initial-scale=1\"> <title>Econovation Recruit</title> <meta name=\"description\" content=\"Recruit site for econovation\"> <style>.hover-a{text-decoration: none;} .hover-a:hover{text-decoration: underline;}</style></head><body style=\"width:fit-content; height: 100vh; max-width: 1920px; margin: auto; display: block; text-align: center;\"> <section style=\"padding-top: 100px;\"><img alt=\"econo-3d-logo\" width=\"114\" height=\"143\" style=\"color:transparent; margin:auto;\" src=\"https://recruit.econovation.kr/images/econo-3d-logo.png\"> <h1 style=\"font-weight: 800; font-size:40px;\">신입모집 지원 완료!</h1> <div style=\"font-size: 18px;\"> <div style=\"margin: 8px;\">에코노베이션 "
                        + year
                        + "기 지원서가 정상적으로 업로드 되었습니다</div> <div style=\"margin: 8px;\">서류 합격 결과는 9월 19일에 개인 메일으로 공지 될 예정입니다.</div> <div style=\"margin: 8px;\"><a class=\"hover-a\" style=\"color: #2160FF; font-weight: 700;\" href=\"https://recruit.econovation.kr/applicant/pdf-viewer?id=%APPLICANT_ID%\">여기</a>에서 지원서를 확인하실 수 있습니다.</div> <div style=\"margin: 8px;\">지원해주셔서 감사합니다.</div> </div> </section></body></html>";
        return html.replace("%APPLICANT_ID%", applicantId);
    }
}
