package com.econovation.recruit.api.email.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import com.econovation.recruit.api.applicant.usecase.ApplicantQueryUseCase;
import com.econovation.recruit.api.email_template.util.DefaultEmailTemplateGenerator;
import com.econovation.recruit.api.recruitment.util.LatestRecruitmentVo;
import com.econovation.recruitdomain.domains.applicant.domain.MongoAnswer;
import com.econovation.recruitdomain.domains.applicant.domain.state.ApplicantState;
import com.econovation.recruitdomain.domains.applicant.domain.state.PassStates;
import com.econovation.recruitdomain.domains.applicant.domain.state.PeriodStates;
import com.econovation.recruitinfrastructure.apache.CommonsEmailSender;
import com.econovation.recruitinfrastructure.slack.SlackMessageProvider;
import com.econovation.recruitinfrastructure.slack.config.SlackProperties;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ApplicantEmailServiceTest {

    private static final String SLACK_URL = "https://example.com/slack-webhook";
    private static final String EMAIL = "applicant@example.com";
    private static final String SUBJECT = "result";
    private static final String TEMPLATE = "mail body";

    @Mock private CommonsEmailSender emailSender;
    @Mock private ApplicantQueryUseCase applicantQueryUseCase;
    @Mock private DefaultEmailTemplateGenerator templateGenerator;
    @Mock private LatestRecruitmentVo latestRecruitInfo;
    @Mock private SlackMessageProvider slackMessageProvider;
    @Mock private SlackProperties slackProperties;

    @InjectMocks private ApplicantEmailService applicantEmailService;

    @ParameterizedTest
    @CsvSource({
        "FIRST_PASSED, 1차 합격",
        "FIRST_FAILED, 1차 불합격",
        "FINAL_PASSED, 최종 합격",
        "FINAL_FAILED, 최종 불합격"
    })
    void sendsSlackOnlyAfterSuccessfulEmail(PassStates passState, String expectedStateName) {
        MongoAnswer applicant = createApplicant(passState);
        mockEmail(applicant, true);
        when(slackProperties.getUrl()).thenReturn(SLACK_URL);

        boolean result = applicantEmailService.sendEmail(applicant);

        assertTrue(result);
        verify(slackMessageProvider)
                .sendMessage(
                        eq(SLACK_URL),
                        argThat(
                                message ->
                                        message.contains("[메일 발송 성공]")
                                                && message.contains("지원자")
                                                && message.contains("백엔드 / 프론트엔드")
                                                && message.contains(expectedStateName)));
    }

    @Test
    void doesNotSendSlackWhenEmailFails() {
        MongoAnswer applicant = createApplicant(PassStates.FIRST_FAILED);
        mockEmail(applicant, false);

        boolean result = applicantEmailService.sendEmail(applicant);

        assertFalse(result);
        verifyNoInteractions(slackMessageProvider, slackProperties);
    }

    private void mockEmail(MongoAnswer applicant, boolean result) {
        when(templateGenerator.generateEmailTemplate(applicant)).thenReturn(TEMPLATE);
        when(templateGenerator.generateSubject(applicant)).thenReturn(SUBJECT);
        when(templateGenerator.getPortfolioFile(applicant)).thenReturn(null);
        when(emailSender.sendEmail(EMAIL, SUBJECT, TEMPLATE)).thenReturn(result);
    }

    private MongoAnswer createApplicant(PassStates passState) {
        ApplicantState applicantState = new ApplicantState();
        switch (passState) {
            case FIRST_PASSED -> applicantState.pass(PeriodStates.FIRST_DISCUSSION);
            case FIRST_FAILED -> applicantState.nonPass(PeriodStates.FIRST_DISCUSSION);
            case FINAL_PASSED -> {
                applicantState.pass(PeriodStates.FIRST_DISCUSSION);
                applicantState.pass(PeriodStates.FINAL_DISCUSSION);
            }
            case FINAL_FAILED -> {
                applicantState.pass(PeriodStates.FIRST_DISCUSSION);
                applicantState.nonPass(PeriodStates.FINAL_DISCUSSION);
            }
            case NON_PROCESSED -> throw new IllegalArgumentException("Unsupported state");
        }

        return MongoAnswer.builder()
                .id("applicant-id")
                .year(2026)
                .qna(
                        Map.of(
                                "email", EMAIL,
                                "name", "지원자",
                                "field1", "백엔드",
                                "field2", "프론트엔드"))
                .applicantState(applicantState)
                .build();
    }
}
