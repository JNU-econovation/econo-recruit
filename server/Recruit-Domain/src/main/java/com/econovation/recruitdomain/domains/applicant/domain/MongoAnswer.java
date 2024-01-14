package com.econovation.recruitdomain.domains.applicant.domain;

import java.util.Map;
import javax.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "applicant")
@AllArgsConstructor
@Getter
@Builder
public class MongoAnswer {
    @Id
    @GeneratedValue(
            generator = "com.econovation.recruitdomain.domains.idGenerator.SnowFlakeGenerator")
    private String id;

    @Field("name")
    private Integer year;

    // shemaless
    @Field("qna")
    private Map<String, Object> qna;
}
