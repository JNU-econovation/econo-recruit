package com.econovation.recruit.api.applicant.handler;

import com.econovation.recruit.api.user.helper.NcpMailHelper;
import com.econovation.recruitdomain.domains.applicant.event.ApplicantRegisterEvent;

import com.econovation.recruitinfrastructure.apache.CommonsEmailSender;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final NcpMailHelper ncpMailHelper;
    private final CommonsEmailSender commonsEmailSender;
    @Async
    @TransactionalEventListener(
            classes = ApplicantRegisterEvent.class,
            phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handle(ApplicantRegisterEvent applicantRegistEvent){
        log.info("%s님의 지원서가 접수되었습니다.", applicantRegistEvent.getUserName());
//        ncpMailHelper.sendMail(
//                "[Econovation] 에코노베이션 지원서 접수 확인 메일",
//                generateConfirmRegisterEmailBody(applicantRegistEvent.getUserName()),
//                applicantRegistEvent.getEmail());
//

        commonsEmailSender.send(
                applicantRegistEvent.getEmail(),
                applicantRegistEvent.getUserName(),
                generateConfirmRegisterEmailBody(applicantRegistEvent.getUserName())
                );
    }
//
//        MimeMessage mimeMessage = generateConfirmRegisterEmail(
//                applicantRegistEvent.getEmail(),
//                applicantRegistEvent.getUserName());
//        emailSenderService.sendEmail(mimeMessage);

//    private SimpleMailMessage generateConfirmRegisterEmail(
//            String email, String userName) {
//        SimpleMailMessage message = emailSenderService.get
//        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
//        try {
//            helper.setFrom(googleMailProperties.getUsername());
//            helper.setTo(email);
//            helper.setSubject("[Econovation] 에코노베이션 지원서 접수 확인 메일");
//            helper.setText(generateConfirmRegisterEmailBody(email, userName), true);
//        } catch (MessagingException e) {
//            log.error("메일 content 생성에 실패하였습니다.. {}", e.getMessage());
//        }
//        return message;
//    }

//    안녕하세요, (지원자이름)님
//2023년 2학기 에코노베이션 26기 신입회원 모집에 지원해주셔서 감사드립니다.
//    지원서는 수정 불가능하며, 서류 합격 여부 및 면접 시간은 9월 19일 화요일에 안내될 예정입니다.
//    더 궁금하신 내용은 카카오톡 채널 "에코노베이션"으로 문의 주시길 바랍니다.
//    감사합니다.
    private String generateConfirmRegisterEmailBody(String userName) {
        return String.format(
                "안녕하세요, %s님\n"
                        + "2023년 2학기 에코노베이션 26기 신입회원 모집에 지원해주셔서 감사드립니다.\n\n"
                        + "지원서는 수정 불가능하며, 서류 합격 여부 및 면접 시간은 9월 19일 화요일에 안내될 예정입니다.\n\n"
                        + "더 궁금하신 내용은 카카오톡 채널 \"에코노베이션\"으로 문의 주시길 바랍니다.\n\n"
                        + "감사합니다.",
                userName);
    }
}
