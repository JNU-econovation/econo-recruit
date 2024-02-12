package com.econovation.recruitdomain.domains.applicant.domain;

import static com.econovation.recruitcommon.utils.FpUtils.toEither;

import com.econovation.recruitcommon.annotation.Adaptor;
import com.econovation.recruitcommon.exception.RecruitCodeException;
import io.vavr.control.Either;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
// vavr

@Adaptor
@RequiredArgsConstructor
public class MongoAnswerAdaptor {
    private final AnswerMongoRepository answerMongoRepository;

    private final MongoTemplate mongoTemplate;

    public void save(MongoAnswer answer) {
        answerMongoRepository.save(answer);
    }

    public Either<RecruitCodeException, MongoAnswer> findById(String applicantId) {
        return toEither(answerMongoRepository.findById(applicantId));
    }

    public List<MongoAnswer> findAll() {
        return answerMongoRepository.findAll();
    }

    public boolean existsByAnswer(String studentId, Integer year) {
        Query query = new Query();
        query.addCriteria(Criteria.where("qna.classOf").is(studentId).and("year").is(year));
        return mongoTemplate.exists(query, MongoAnswer.class);
    }
}
