package com.econovation.recruitdomain.domains.email_template.domain;

import com.econovation.recruitdomain.domains.BaseTimeEntity;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class EmailTemplate extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "email_template_id")
    private Long id;

    @Column(name = "template_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private EmailTemplateType emailTemplateType;

    @Column(name = "content", columnDefinition = "TEXT")
    private String message;

    // 예약 발송 시간

    @Column(name = "scheduled_time")
    private LocalDateTime scheduledTime;

    public void convertMessageToHtml() {
        //        this.message =
        // PREFIX_EMATIL_TEMPLATE.concat(this.message).concat(SUFFIX_EMATIL_TEMPLATE);
    }
}
