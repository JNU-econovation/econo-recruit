package com.econovation.recruit.api.applicant.service;

import com.econovation.recruit.api.applicant.usecase.ApplicantMongoLoadUseCase;
import com.econovation.recruit.api.applicant.usecase.ApplicantMongoRegisterUseCase;
import com.econovation.recruitdomain.domains.applicant.domain.MongoAnswer;
import com.econovation.recruitdomain.domains.applicant.domain.MongoAnswerAdaptor;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerMongoService
        implements ApplicantMongoRegisterUseCase, ApplicantMongoLoadUseCase {
    private final MongoAnswerAdaptor answerAdaptor;
    private final CommandGateway commandGateway;

    @Value("${econovation.year}")
    private Integer year;

    @Override
    public void execute(Map<String, Object> qna) {
        MongoAnswer answer = MongoAnswer.builder().qna(qna).year(year).build();
        answerAdaptor.save(answer);
    }

    //    public CompletableFuture<String> createApplicant(Map<String, Object> qna) {
    //        String id = generateUniqueId();
    //
    //        CreateAnswerCommand command = new CreateAnswerCommand(id, year, qna);
    //
    //        return commandGateway.send(command);
    //    }

    private String generateUniqueId() {
        return UUID.randomUUID().toString();
    }

    @Override
    public Map<String, Object> execute(String applicantId) {
        return answerAdaptor
                .findById(applicantId)
                .fold(
                        (exception) -> {
                            throw exception;
                        },
                        (answer) -> {
                            return answer.getQna();
                        });
    }
}
