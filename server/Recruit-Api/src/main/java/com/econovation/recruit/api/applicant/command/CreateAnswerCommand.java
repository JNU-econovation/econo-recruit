package com.econovation.recruit.api.applicant.command;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@AllArgsConstructor
@ToString
@Data
@NoArgsConstructor
@Getter
public class CreateAnswerCommand {
    @TargetAggregateIdentifier private String id;
    private Integer year;
    private Map<String, Object> qna;
}
