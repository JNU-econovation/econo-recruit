package com.econovation.recruit.api.applicant.service;

import com.econovation.recruit.api.applicant.usecase.ApplicantRegisterUseCase;
import com.econovation.recruitcommon.utils.Result;
import com.econovation.recruitdomain.common.aop.domainEvent.Events;
import com.econovation.recruitdomain.common.events.applicant.SubmitApplicantEvent;
import com.econovation.recruitdomain.domain.applicant.Applicant;
import com.econovation.recruitdomain.domains.applicant.adaptor.AnswerAdaptor;
import com.econovation.recruitdomain.domains.applicant.adaptor.ApplicantAdaptor;
import com.econovation.recruitdomain.domains.applicant.adaptor.QuestionAdaptor;
import com.econovation.recruitdomain.domains.applicant.domain.Answer;
import com.econovation.recruitdomain.domains.applicant.domain.Question;
import com.econovation.recruitdomain.domains.applicant.dto.BlockRequestDto;
import com.econovation.recruitdomain.domains.applicant.exception.ApplicantNotFoundException;
import com.econovation.recruitdomain.domains.applicant.exception.QuestionNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicantService implements ApplicantRegisterUseCase {
    private final QuestionAdaptor questionAdaptor;
    private final ApplicantAdaptor applicantAdaptor;
    private final AnswerAdaptor answerAdaptor;

    @Transactional
    @Override
    public void execute(List<BlockRequestDto> blocks) {
        List<Question> questions = questionAdaptor.findAll();
        UUID applicantId = UUID.randomUUID();
        List<Answer> results =
                blocks.stream()
                        .map(
                                block -> {
                                    Result<Question> matchingQuestionResult =
                                            questions.stream()
                                                    .filter(
                                                            question ->
                                                                    question.getName()
                                                                            .equals(
                                                                                    block
                                                                                            .getName()))
                                                    .findFirst()
                                                    .map(Result::success)
                                                    .orElseThrow(
                                                            () ->
                                                                    QuestionNotFoundException
                                                                            .EXCEPTION);
                                    return matchingQuestionResult
                                            .map(
                                                    question ->
                                                            Answer.builder()
                                                                    .question(question)
                                                                    .answer(block.getAnswer())
                                                                    .applicantId(applicantId)
                                                                    .build())
                                            .getValue();
                                })
                        .collect(Collectors.toList());
        // Result 를 save 하게 된다.
        answerAdaptor.saveAll(results);
        Events.raise(
                SubmitApplicantEvent.of(applicantId, convertToSubmitApplicantEventTitle(results)));
        // TODO: 추가될지 말지 결정해야 함
        //        applicantRegister(results);
    }

    private String convertToSubmitApplicantEventTitle(List<Answer> results) {
        final String HOPE_FIELD_QUESTION_KEY = "hopeField";
        final String NAME_QUESTION_KEY = "name";

        StringBuilder titleBuilder = new StringBuilder();
        String hopeField = "";
        String name = "";

        for (Answer answer : results) {
            String question = answer.getQuestion().getName();
            String userInputValue = answer.getAnswer();

            if (question.equals(HOPE_FIELD_QUESTION_KEY)) {
                hopeField = userInputValue;
            }
            if (question.equals(NAME_QUESTION_KEY)) {
                name = userInputValue;
            }
        }

        titleBuilder.append("[").append(hopeField).append("] ").append(name);
        return titleBuilder.toString();
    }

    private Applicant createApplicantFromUserInput(Map<String, String> userInput) {
        return Applicant.builder()
                .id(UUID.randomUUID())
                .hopeField(userInput.get("hopeField"))
                .firstPriority(userInput.get("firstPriority"))
                .secondPriority(userInput.get("secondPriority"))
                .name(userInput.get("name"))
                .phoneNumber(userInput.get("phoneNumber"))
                .studentId(Integer.parseInt(userInput.get("studentId")))
                .grade(Integer.parseInt(userInput.get("grade")))
                .semester(Integer.parseInt(userInput.get("semester")))
                .major(userInput.get("major"))
                .doubleMajor(userInput.get("doubleMajor"))
                .minor(userInput.get("minor"))
                .supportPath(userInput.get("supportPath"))
                .email(userInput.get("email"))
                .build();
    }
}
