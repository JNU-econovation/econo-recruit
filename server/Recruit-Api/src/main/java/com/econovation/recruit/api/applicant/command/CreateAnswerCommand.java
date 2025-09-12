package com.econovation.recruit.api.applicant.command;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@ToString
@Data
@NoArgsConstructor
@Getter
public class CreateAnswerCommand {
    @TargetAggregateIdentifier private String id;
    private Integer year;
    private Map<String, Object> qna;

    public CreateAnswerCommand(String id, Integer year, Map<String, Object> qna) {
        this.id = id;
        this.year = year;
        setQna(qna);
    }

    private void setQna(Map<String, Object> qna) {
        if (qna != null && qna.containsKey("name")) {
            Object value = qna.get("name");
            if (value instanceof String str) {
                qna.put("name", str.trim());
            }
        }
        this.qna = qna;
    }
}
