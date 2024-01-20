package com.econovation.recruit.api.applicant.service;

import com.econovation.recruit.api.applicant.usecase.ApplicantMongoLoadUseCase;
import com.econovation.recruit.api.applicant.usecase.ApplicantMongoRegisterUseCase;
import com.econovation.recruitcommon.exception.RecruitCodeException;
import com.econovation.recruitdomain.domains.applicant.domain.MongoAnswer;
import com.econovation.recruitdomain.domains.applicant.domain.MongoAnswerAdaptor;
import io.vavr.control.Either;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerMongoService implements ApplicantMongoRegisterUseCase,
    ApplicantMongoLoadUseCase {
  private final MongoAnswerAdaptor answerAdaptor;

  @Value("${econovation.year}")
  private Integer year;

  @Override
  public void execute(Map<String, Object> qna) {
    MongoAnswer answer = MongoAnswer.builder()
        .qna(qna)
        .year(year)
        .build();
    answerAdaptor.save(answer);
  }

  @Override
  public Map<String, Object> execute(String applicantId) {
    return answerAdaptor.findById(applicantId).fold(
          (exception) -> {
            throw exception;
          },
          (answer) -> {
            return answer.getQna();
          }
    );
  }
}
