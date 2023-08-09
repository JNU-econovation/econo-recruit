package com.econovation.recruitdomain.domains.applicant.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;
import com.querydsl.core.types.dsl.PathInits;
import javax.annotation.processing.Generated;

/** QAnswer is a Querydsl query type for Answer */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAnswer extends EntityPathBase<Answer> {

    private static final long serialVersionUID = -1573111897L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAnswer answer1 = new QAnswer("answer1");

    public final StringPath answer = createString("answer");

    public final ComparablePath<java.util.UUID> applicantId =
            createComparable("applicantId", java.util.UUID.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QQuestion question;

    public QAnswer(String variable) {
        this(Answer.class, forVariable(variable), INITS);
    }

    public QAnswer(Path<? extends Answer> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAnswer(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAnswer(PathMetadata metadata, PathInits inits) {
        this(Answer.class, metadata, inits);
    }

    public QAnswer(Class<? extends Answer> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.question =
                inits.isInitialized("question") ? new QQuestion(forProperty("question")) : null;
    }
}
