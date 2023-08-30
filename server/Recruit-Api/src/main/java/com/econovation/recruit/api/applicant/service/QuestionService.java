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
        questionAdaptor.saveAll(
                questions.stream()
                        .map(
                                question ->
                                        Question.builder()
                                                .questionType(question.getType())
                                                .name(question.getName())
                                                .parentId(question.getParentId())
                                                .build())
                        .collect(Collectors.toList()));
    }
}
