package com.econovation.recruitdomain.domains.applicant.helper;

import com.econovation.recruitcommon.annotation.Helper;
import com.econovation.recruitdomain.domains.applicant.domain.Answer;
import com.econovation.recruitdomain.domains.applicant.dto.ApplicantPdfDto;
import com.econovation.recruitinfrastructure.pdf.PdfRender;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lowagie.text.DocumentException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Helper
@RequiredArgsConstructor
public class ApplicantPdfHelper {
    private final PdfRender pdfRender;

    private final ObjectMapper objectMapper;

    private final SpringTemplateEngine templateEngine;

    //    private final javaMailSender mailSender;

    public void sendApplicantPdf(List<Answer> answer) throws DocumentException, IOException {
        // 지원서 관련 타임리프 파일.
        ApplicantPdfDto settlementPDFDto = getApplicantPDFDto(answer);
        String html = templateEngine.process("applicant", getPdfHtmlContext(settlementPDFDto));
        ByteArrayOutputStream outputStream = pdfRender.generatePdfFromHtml(html);
        //        mailSender.sendMailWithAttachment()
    }

    private Context getPdfHtmlContext(ApplicantPdfDto settlementPDFDto) {
        Map result = objectMapper.convertValue(settlementPDFDto, Map.class);

        Context context = new Context(null, result);
        context.setVariable("now", LocalDateTime.now());
        return context;
    }

    private ApplicantPdfDto getApplicantPDFDto(List<Answer> answers) {
        LinkedHashMap<String, String> result =
                answers.stream()
                        .collect(
                                Collectors.toMap(
                                        answer -> answer.getQuestion().getName(),
                                        Answer::getAnswer,
                                        (existing, replacement) -> existing,
                                        LinkedHashMap::new));
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
