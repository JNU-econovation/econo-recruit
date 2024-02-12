package com.econovation.recruitdomain.domains.applicant.event.aggregateevent;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AnswerCreatedEvent {
    private String id;
    private Integer year;
    private Map<String, Object> qna;
}
