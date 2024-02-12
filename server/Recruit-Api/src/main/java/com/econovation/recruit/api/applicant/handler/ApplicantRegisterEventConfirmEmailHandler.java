package com.econovation.recruit.api.applicant.handler;

import com.econovation.recruitdomain.domains.applicant.event.domainevent.ApplicantRegisterEvent;
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
    private final CommonsEmailSender commonsEmailSender;

    @Async
    @TransactionalEventListener(
            classes = ApplicantRegisterEvent.class,
            phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handle(ApplicantRegisterEvent applicantRegistEvent) {
        log.info(applicantRegistEvent.getUserName() + "님의 지원서가 접수되었습니다.");
        commonsEmailSender.send(
                applicantRegistEvent.getEmail(), applicantRegistEvent.getApplicantId());
    }

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
