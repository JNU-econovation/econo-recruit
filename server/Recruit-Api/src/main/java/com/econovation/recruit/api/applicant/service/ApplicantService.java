package com.econovation.recruit.api.applicant.service;

import static com.econovation.recruitcommon.consts.RecruitStatic.APPLICANT_SEPERATOR_LIST;

import com.econovation.recruit.api.applicant.usecase.ApplicantRegisterUseCase;
import com.econovation.recruitcommon.utils.Result;
import com.econovation.recruitdomain.common.aop.domainEvent.Events;
import com.econovation.recruitdomain.common.events.applicant.SubmitApplicantEvent;
import com.econovation.recruitdomain.domain.applicant.Applicant;
import com.econovation.recruitdomain.domains.applicant.adapter.AnswerAdaptor;
import com.econovation.recruitdomain.domains.applicant.adapter.ApplicantAdaptor;
import com.econovation.recruitdomain.domains.applicant.adapter.QuestionAdaptor;
import com.econovation.recruitdomain.domains.applicant.domain.Answer;
import com.econovation.recruitdomain.domains.applicant.domain.Question;
import com.econovation.recruitdomain.domains.applicant.dto.BlockRequestDto;
import com.econovation.recruitdomain.domains.applicant.exception.ApplicantNotFoundException;
import java.util.HashMap;
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

        List<Answer> results =
                blocks.stream()
                        .map(
                                block -> {
                                    Result<Question> matchingQuestionResult =
                                            questions.stream()
                                                    .filter(
                                                            question ->
                                                                    question.getTitle()
                                                                            .equals(
                                                                                    block
                                                                                            .getName()))
                                                    .findFirst()
                                                    .map(Result::success)
                                                    .orElse(
                                                            Result.failure(
                                                                    ApplicantNotFoundException
                                                                            .EXCEPTION));

                                    return matchingQuestionResult
                                            .map(
                                                    question ->
                                                            Answer.builder()
                                                                    .question(question)
                                                                    .answer(block.getAnswer())
                                                                    .applicantId(UUID.randomUUID())
                                                                    .build())
                                            .getValue();
                                })
                        .collect(Collectors.toList());
        // TODO: 추가될지 말지 결정해야 함
        //        answerAdaptor.saveAll(results);
        applicantRegister(results);
    }

    private void applicantRegister(List<Answer> results) {
        Applicant applicant = convertToApplicant(results);
        applicantAdaptor.save(applicant);
        Events.raise(SubmitApplicantEvent.from(applicant.getId()));
    }

    private Applicant convertToApplicant(List<Answer> results) {
        // 사용자로부터 입력을 받을 HashMap 생성
        Map<String, String> userInput = new HashMap<>();

        // 사용자로부터 각 질문에 대한 입력 값을 받음
        for (Answer answer : results) {
            String question = answer.getQuestion().getTitle();
            String userInputValue = answer.getAnswer();
            for (Map.Entry<String, String> entry : APPLICANT_SEPERATOR_LIST) {
                if (question.equals(entry.getKey())) {
                    userInput.put(entry.getValue(), userInputValue);
                }
            }
        }
        // 사용자 입력 값을 사용하여 Applicant 엔티티를 생성
        return createApplicantFromUserInput(userInput);
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
