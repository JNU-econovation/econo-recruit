package com.econovation.recruitdomain.domains.applicant.adaptor;

import com.econovation.recruitcommon.annotation.Adaptor;
import com.econovation.recruitdomain.domains.applicant.domain.AnswerMongoRepository;
import com.econovation.recruitdomain.domains.applicant.domain.MongoAnswer;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@Adaptor
@RequiredArgsConstructor
public class AnswerAdaptor {
    private final MongoTemplate mongoTemplate;
    private final AnswerMongoRepository answerRepository;

    public void save(MongoAnswer answer) {
        answerRepository.save(answer);
    }

    public void saveAll(List<MongoAnswer> answers) {
        answerRepository.saveAll(answers);
    }

    public List<MongoAnswer> findAll() {
        return answerRepository.findAll();
    }

    public List<MongoAnswer> findByYear(Integer year, Integer page) {
        Query query =
                new Query()
                        .with(Sort.by(Sort.Direction.DESC, "createdAt")) // Optional: 정렬 설정
                        .addCriteria(Criteria.where("year").is(year))
                        .skip((page - 1) * 10L) // offset 설정
                        .limit(10); // 페이지 크기 설정
        return mongoTemplate.find(query, MongoAnswer.class);
    }

    public long getTotalCountByYear(Integer year) {
        return mongoTemplate.count(Query.query(Criteria.where("year").is(year)), MongoAnswer.class);
    }
}
