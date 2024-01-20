package com.econovation.recruitdomain.domains.applicant.domain;

import static com.econovation.recruitcommon.utils.FpUtils.toEither;
import static io.vavr.API.$;
import static io.vavr.API.Case;

import com.econovation.recruitcommon.annotation.Adaptor;
import com.econovation.recruitcommon.exception.RecruitCodeException;
import io.vavr.control.Either;
import io.vavr.control.Option;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
//vavr


@Adaptor
@RequiredArgsConstructor
public class MongoAnswerAdaptor {
    private final AnswerMongoDBRepository answerMongoDBRepository;

    public void save(MongoAnswer answer) {
        answerMongoDBRepository.save(answer);
    }

    public Either<RecruitCodeException, MongoAnswer> findById(String applicantId) {
        return toEither(answerMongoDBRepository.findById(applicantId));
    }
    public List<MongoAnswer> findByYear(Integer year) {
        return Option.of(answerMongoDBRepository.findByYear(year)).getOrElse(Collections.emptyList());
    }
}
