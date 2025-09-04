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
    private final MongoAnswerRepository mongoAnswerRepository;

    private final MongoTemplate mongoTemplate;

    public void save(MongoAnswer answer) {
        mongoAnswerRepository.save(answer);
    }

    public Either<RecruitCodeException, MongoAnswer> findById(String applicantId) {
        return toEither(mongoAnswerRepository.findById(applicantId));
    }

    public List<MongoAnswer> findAll() {
        return mongoAnswerRepository.findAll();
    }

    public boolean existsByAnswer(String studentId, Integer year) {
        Query query = new Query();
        query.addCriteria(Criteria.where("qna.classOf").is(studentId).and("year").is(year));
        return mongoTemplate.exists(query, MongoAnswer.class);
    }

    public void delete(Integer year) {
        // TODO: year에 해당하는 지원자 id 리스트로 뽑기

        mongoAnswerRepository.deleteByYear(year);
        // TODO: 지원자 id 리스트를 가지고 time_table 데이터 삭제하기 delete(List<String> applicantIds, Integer year)

        // TODO: 지원자 id 리스트를 가지고 score 데이터 삭제하기

        // TODO: 지원자 id 리스트를 가지고 label 데이터 삭제하기


        // TODO: card 테이블에서 지원자 id 리스트에 대응하는 board_id 조회하기

        // TODO: 이전에서 조회한 board_id 리스트에 대응하는 board 데이터 삭제하기

        // TODO: card 테이블에서 지원자 id 리스트에 대응하는 데이터 삭제하기

    }
}
