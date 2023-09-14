package com.econovation.recruit.api.applicant.service;

import com.econovation.recruit.api.applicant.usecase.QuestionRegisterUseCase;
import com.econovation.recruitdomain.domains.applicant.adaptor.QuestionAdaptor;
import com.econovation.recruitdomain.domains.applicant.domain.Question;
import com.econovation.recruitdomain.domains.dto.QuestionRequestDto;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionService implements QuestionRegisterUseCase {
    private final QuestionAdaptor questionAdaptor;

    @Override
    public void execute(String type, String name, Integer parentId) {
        questionAdaptor.save(
                Question.builder().questionType(type).name(name).parentId(parentId).build());
    }

    @Override
    public void execute(List<QuestionRequestDto> questions) {
        // DB 내에 있는 질문은 기존에 있는 것은 생략하고 저장한다
        List<Question> questionList = questionAdaptor.findAll();
        List<Question> filteredQuestions =
                questions.stream()
                        .filter(
                                question ->
                                        questionList.stream()
                                                .noneMatch(
                                                        q ->
                                                                q.getName()
                                                                        .equals(
                                                                                question
                                                                                        .getName())))
                        .map(
                                question ->
                                        Question.builder()
                                                .questionType(question.getType())
                                                .name(question.getName())
                                                .parentId(question.getParentId())
                                                .build())
                        .collect(Collectors.toList());
        questionAdaptor.saveAll(filteredQuestions);
    }
}
