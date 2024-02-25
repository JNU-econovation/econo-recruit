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
                        .with(Sort.by(Sort.Direction.DESC, "created_date")) // Optional: 정렬 설정
                        .addCriteria(Criteria.where("year").is(year))
                        .skip((page - 1) * 10L) // offset 설정
                        .limit(10); // 페이지 크기 설정
        return mongoTemplate.find(query, MongoAnswer.class);
    }

    public long getTotalCountByYear(Integer year) {
        return mongoTemplate.count(Query.query(Criteria.where("year").is(year)), MongoAnswer.class);
    }

    public List<MongoAnswer> findByApplicantIds(List<String> applicantIds) {
        return answerRepository.findByIdIn(applicantIds);
    }

    public List<MongoAnswer> findBySearchKeyword(String searchKeyword) {
        Query query = new Query();
        /**
         * {"field": "\"디자이너\"", "field1": "\"GAME\"", "field2": "\"WEB\"", "name": "\"이서현2\"",
         * "contacted": "\"123-1312-3123\"", "classOf": new NumberInt("23"), "email":
         * "\"ymecca730135@gmail.com\"", "registered": "\"휴학\"", "grade": "\"4학년\"", "semester":
         * "\"2학기\"", "major": "\"디자인학과\"", "doubleMajor": "\"\"", "minor": "\"\"", "activity":
         * "\"없음\"", "channel": "[\"학과 공지사항\",\"페이스북\"]", "reason":
         * "\"디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너\"",
         * "future":
         * "\"디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너\"",
         * "workDescript":
         * "\"디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너\"",
         * "keyword":
         * "\"디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너\"",
         * "betterment": "\"디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너\"",
         * "drain": "\"디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너\"", "collaboration":
         * "\"디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너\"", "studyPlan":
         * "\"디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너디자이너\"", "portfolio":
         * "\"디자이너디자이너디자이너디자이너\"", "fileUrl": "\"디자이너디자이너디자이너디자이너\"", "check": "\"확인했습니다\"",
         * "personalInformationAgree": "\"동의함\""}
         */
        //        query.addCriteria(
        //                new Criteria()
        //                        .orOperator(
        //                                Criteria.where("qna.field").regex(searchKeyword),
        //                                Criteria.where("qna.field1").regex(searchKeyword)
        //                                Criteria.where("qna.name").regex(searchKeyword),
        //                        )
        //        );
        //        answerRepository.search(searchKeyword);
        return null;
    }
}
