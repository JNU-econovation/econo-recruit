package com.econovation.recruitdomain.domains.applicant.domain;

import com.econovation.recruitcommon.annotation.Adaptor;
import lombok.RequiredArgsConstructor;

@Adaptor
@RequiredArgsConstructor
public class MongoAnswerAdaptor {
    private final AnswerMongoDBRepository answerMongoDBRepository;

    public void save(MongoAnswer answer) {
        answerMongoDBRepository.save(answer);
    }
}
