package com.econovation.recruitdomain.domains.email_template.event;

import com.econovation.recruitdomain.common.aop.domainEvent.DomainEvent;
import com.econovation.recruitdomain.domains.email_template.domain.EmailTemplateType;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class EmailSendEvent extends DomainEvent {
    private final String applicantId;
    private final EmailTemplateType emailTemplateType;
    private final String message;
    private final boolean slackNotify;
    private final String slackUrl;
    private final String name;
    private final String field1;
    private final String field2;

    public static EmailSendEvent of(
            String applicantId, String emailTemplateTypeString, String message) {
        return EmailSendEvent.builder()
                .applicantId(applicantId)
                .emailTemplateType(EmailTemplateType.valueOf(emailTemplateTypeString))
                .message(message)
                .slackNotify(false)
                .slackUrl(null)
                .build();
    }

    public static EmailSendEvent of(
            String applicantId,
            String emailTemplateTypeString,
            boolean slackNotify,
            String slackUrl,
            String name,
            String field1,
            String field2) {
        return EmailSendEvent.builder()
                .applicantId(applicantId)
                .emailTemplateType(EmailTemplateType.valueOf(emailTemplateTypeString))
                .message("")
                .slackNotify(slackNotify)
                .slackUrl(slackUrl)
                .name(name)
                .field1(field1)
                .field2(field2)
                .build();
    }
}
