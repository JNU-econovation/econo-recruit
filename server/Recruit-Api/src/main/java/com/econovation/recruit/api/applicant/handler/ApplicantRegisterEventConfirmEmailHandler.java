package com.econovation.recruit.api.applicant.handler;

import com.econovation.recruit.api.card.usecase.BoardRegisterUseCase;
import com.econovation.recruitdomain.domains.applicant.event.ApplicantRegisterEvent;
import com.econovation.recruitdomain.domains.card.adaptor.CardAdaptor;
import com.econovation.recruitdomain.domains.card.domain.Card;
import com.econovation.recruitinfrastructure.mail.EmailSenderService;
import com.econovation.recruitinfrastructure.mail.GoogleMailProperties;
import com.econovation.recruitinfrastructure.ses.AwsSesUtils;
import com.econovation.recruitinfrastructure.ses.SendRawEmailDto;
import com.econovation.recruitinfrastructure.slack.SlackMessageProvider;
import com.econovation.recruitinfrastructure.slack.config.SlackProperties;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
@Slf4j
public class ApplicantRegisterEventConfirmEmailHandler {
    private final GoogleMailProperties googleMailProperties;
    private final EmailSenderService emailSenderService;
    private final AwsSesUtils awsSesUtils;
    @Async
    @TransactionalEventListener(
            classes = ApplicantRegisterEvent.class,
            phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handle(ApplicantRegisterEvent applicantRegistEvent) {
        log.info("%s님의 지원서가 접수되었습니다.", applicantRegistEvent.getUserName());
        try{
            SendRawEmailDto sendData = SendRawEmailDto.builder()
                    .bodyHtml(generateConfirmRegisterEmailBody(applicantRegistEvent.getEmail(), applicantRegistEvent.getUserName()))
                    .recipient(applicantRegistEvent.getEmail())
                    .subject("[Econovation] 에코노베이션 지원서 접수 확인 메일")
                    .build();
            awsSesUtils.sendRawEmails(sendData);
        } catch (MessagingException e) {
            log.error("메일 content 생성에 실패하였습니다.. {}", e.getMessage());
        }
    }
//
//        MimeMessage mimeMessage = generateConfirmRegisterEmail(
//                applicantRegistEvent.getEmail(),
//                applicantRegistEvent.getUserName());
//        emailSenderService.sendEmail(mimeMessage);

/*    private SimpleMailMessage generateConfirmRegisterEmail(
            String email, String userName) {
        SimpleMailMessage message = emailSenderService.
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
        try {
            helper.setFrom(googleMailProperties.getUsername());
            helper.setTo(email);
            helper.setSubject("[Econovation] 에코노베이션 지원서 접수 확인 메일");
            helper.setText(generateConfirmRegisterEmailBody(email, userName), true);
        } catch (MessagingException e) {
            log.error("메일 content 생성에 실패하였습니다.. {}", e.getMessage());
        }
        return message;
    }*/

    private String generateConfirmRegisterEmailBody(String email, String userName) {
        return String.format(
                "안녕하세요 %s님,\n\n"
                        + "저희 에코노베이션에 지원해주셔서 진심으로 감사드립니다.\n\n"
                        + "귀하의 지원이 성공적으로 접수되었음을 알려드립니다. "
                        + "저희 팀은 지원서를 신중히 검토한 후, 빠른 시일 내에 연락드리겠습니다.\n\n"
                        + "혹시 궁금한 사항이 있으시면 언제든지 저희에게 연락주시기 바랍니다.\n\n"
                        + "다시 한번 감사의 말씀을 드리며, 좋은 결과가 있기를 바랍니다.\n\n"
                        + "감사합니다,\n"
                        + "- 에코노베이션 Recruit팀",
                userName);
    }
}
