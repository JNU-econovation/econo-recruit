package com.econovation.recruitdomain.domains.applicant.helper;

import com.econovation.recruitcommon.annotation.Helper;
import com.econovation.recruitdomain.domains.applicant.dto.ApplicantPdfDto;
import com.econovation.recruitinfrastructure.mail.EmailSenderService;
import com.econovation.recruitinfrastructure.pdf.PdfRender;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.istack.ByteArrayDataSource;
import java.time.LocalDateTime;
import java.util.Map;
import javax.mail.IllegalWriteException;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Helper
@RequiredArgsConstructor
public class ApplicantPdfHelper {
    private final PdfRender pdfRender;

    private final ObjectMapper objectMapper;

    private final SpringTemplateEngine templateEngine;
    private final EmailSenderService mailSender;

    private Context getPdfHtmlContext(ApplicantPdfDto settlementPDFDto) {
        Map result = objectMapper.convertValue(settlementPDFDto, Map.class);

        Context context = new Context(null, result);
        context.setVariable("now", LocalDateTime.now());
        return context;
    }

    public void sendToInterviewers(Map<String, String> answer) throws MessagingException {
        ApplicantPdfDto applicantPdfDto = getApplicantPDFDto(answer);
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

            messageHelper.setFrom("ymecca730135@gmail.com");
            //            messageHelper.setTo(applicantPdfDto.getEmail());
            messageHelper.setTo("ymecca12@naver.com");
            messageHelper.setCc("Econovation_Recruit_Team");
            messageHelper.setSubject("Econovation 신입모집 지원서");
            messageHelper.setText("귀역뛰 ( 귀하의 역량은 뛰어나나... )");
            String applicant =
                    templateEngine.process("applicant", getPdfHtmlContext(applicantPdfDto));
            messageHelper.addAttachment(
                    "Econovation_Recruit_Team_신입모집_지원서.pdf",
                    new ByteArrayDataSource(
                            pdfRender.generatePdfFromHtml(applicant).toByteArray(),
                            "application/pdf"));
            mailSender.sendEmail(messageHelper.getMimeMessage());

        } catch (Exception e) {
            throw new IllegalWriteException("메일발송에 실패하였습니다.");
        }
    }

    private ApplicantPdfDto getApplicantPDFDto(Map<String, String> result) {
        return ApplicantPdfDto.builder()
                .field(result.get("field"))
                .field1(result.get("field1"))
                .field2(result.get("field2"))
                .name(result.get("name"))
                .contacted(result.get("contacted"))
                .classOf(result.get("classOf"))
                .grade(result.get("grade"))
                .semester(result.get("semester"))
                .major(result.get("major"))
                .doubleMajor(result.get("doubleMajor"))
                .minor(result.get("minor"))
                .activity(result.get("activity"))
                .channel(result.get("channel"))
                .reason(result.get("reason"))
                .future(result.get("future"))
                .experience(result.get("experience"))
                .experienceTextarea(result.get("experienceTextarea"))
                .goal(result.get("goal"))
                .deep(result.get("deep"))
                .collaboration(result.get("collaboration"))
                .portfolio(result.get("portfolio"))
                .fileUrl(result.get("fileUrl"))
                .fileUrlforPlanner(result.get("fileUrlforPlanner"))
                .email(result.get("email"))
                .check(result.get("check"))
                .personalInformationAgree(result.get("personalInformationAgree"))
                .personalInformationAgreeForPortfolio(
                        result.get("personalInformationAgreeForPortfolio"))
                .build();
    }
}
