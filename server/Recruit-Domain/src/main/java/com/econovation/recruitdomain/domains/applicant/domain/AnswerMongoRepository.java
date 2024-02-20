package com.econovation.recruitdomain.domains.applicant.domain;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AnswerMongoRepository extends MongoRepository<MongoAnswer, String> {

    List<MongoAnswer> findByIdIn(List<String> applicantIds);
}
