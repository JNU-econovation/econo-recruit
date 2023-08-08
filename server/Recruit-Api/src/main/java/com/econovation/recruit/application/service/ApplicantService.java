package com.econovation.recruit.application.service;


import com.econovation.recruit.application.port.in.ApplicantRegisterUseCase;
import com.econovation.recruitcommon.utils.Result;
import com.econovation.recruitdomain.domains.applicant.adapter.AnswerAdaptor;
import com.econovation.recruitdomain.domains.applicant.adapter.QuestionAdaptor;
import com.econovation.recruitdomain.domains.applicant.domain.Answer;
import com.econovation.recruitdomain.domains.applicant.domain.Question;
import com.econovation.recruitdomain.domains.applicant.dto.BlockRequestDto;
import com.econovation.recruitdomain.domains.applicant.exception.ApplicantDuplicateSubmitException;
import com.econovation.recruitdomain.domains.applicant.exception.ApplicantNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicantService implements ApplicantRegisterUseCase {
    private final QuestionAdaptor questionAdaptor;
    private final AnswerAdaptor answerAdaptor;

    @Override
    public void execute(List<BlockRequestDto> blocks) {
        List<Question> questions = questionAdaptor.findAll();

        List<Answer> results = blocks.stream()
                .map(block -> {
                    Result<Question> matchingQuestionResult = questions.stream()
                            .filter(question -> question.getTitle().equals(block.getName()))
                            .findFirst()
                            .map(Result::success)
                            .orElse(Result.failure(ApplicantNotFoundException.EXCEPTION));

                    return matchingQuestionResult
                            .map(question -> Answer.builder()
                                    .question(question)
                                    .answer(block.getAnswer())
                                    .build())
                            .getValue();
                })
                .collect(Collectors.toList());

        answerAdaptor.saveAll(results);
    }
}
