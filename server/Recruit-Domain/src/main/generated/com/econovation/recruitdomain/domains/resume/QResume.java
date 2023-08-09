package com.econovation.recruitdomain.domains.resume;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;
import javax.annotation.processing.Generated;

/** QResume is a Querydsl query type for Resume */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QResume extends EntityPathBase<Resume> {

    private static final long serialVersionUID = -716048327L;

    public static final QResume resume = new QResume("resume");

    public final StringPath answer = createString("answer");

    public final NumberPath<Applicant> applicant = createNumber("applicant", Applicant.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> questionId = createNumber("questionId", Integer.class);

    public QResume(String variable) {
        super(Resume.class, forVariable(variable));
    }

    public QResume(Path<? extends Resume> path) {
        super(path.getType(), path.getMetadata());
    }

    public QResume(PathMetadata metadata) {
        super(Resume.class, metadata);
    }
}
