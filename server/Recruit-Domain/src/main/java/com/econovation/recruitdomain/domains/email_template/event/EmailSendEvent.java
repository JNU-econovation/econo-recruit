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

    public static EmailSendEvent of(
            String applicantId, String emailTemplateTypeString, String message) {
        return EmailSendEvent.builder()
                .applicantId(applicantId)
                .emailTemplateType(EmailTemplateType.valueOf(emailTemplateTypeString))
                .message(message)
                .build();
    }
}
