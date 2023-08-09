package com.econovation.recruitdomain.domains.applicant.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;
import javax.annotation.processing.Generated;

/** QQuestion is a Querydsl query type for Question */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QQuestion extends EntityPathBase<Question> {

    private static final long serialVersionUID = -598352497L;

    public static final QQuestion question = new QQuestion("question");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath questionType = createString("questionType");

    public final StringPath title = createString("title");

    public QQuestion(String variable) {
        super(Question.class, forVariable(variable));
    }

    public QQuestion(Path<? extends Question> path) {
        super(path.getType(), path.getMetadata());
    }

    public QQuestion(PathMetadata metadata) {
        super(Question.class, metadata);
    }
}
