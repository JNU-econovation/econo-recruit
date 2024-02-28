package com.econovation.recruitdomain.domains.applicant.domain;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoAnswerRepository extends MongoRepository<MongoAnswer, String> {

    List<MongoAnswer> findByIdIn(List<String> applicantIds);

    //    @Query("{'qna': {$regex: ?0, $options: 'i', $limit: 10}}")
    //    List<MongoAnswer> search(String searchKeyword);
}
