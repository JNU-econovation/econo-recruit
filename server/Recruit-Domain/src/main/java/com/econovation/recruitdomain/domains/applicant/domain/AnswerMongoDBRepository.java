package com.econovation.recruitdomain.domains.applicant.domain;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerMongoDBRepository extends MongoRepository<MongoAnswer, String> {
    List<MongoAnswer> findByYear(Integer year);
}
