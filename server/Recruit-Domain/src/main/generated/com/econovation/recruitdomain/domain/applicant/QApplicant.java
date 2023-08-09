package com.econovation.recruitdomain.domain.applicant;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;
import javax.annotation.processing.Generated;

/** QApplicant is a Querydsl query type for Applicant */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QApplicant extends EntityPathBase<Applicant> {

    private static final long serialVersionUID = -574248318L;

    public static final QApplicant applicant = new QApplicant("applicant");

    public final com.econovation.recruitdomain.domains.QBaseTimeEntity _super =
            new com.econovation.recruitdomain.domains.QBaseTimeEntity(this);

    // inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath doubleMajor = createString("doubleMajor");

    public final StringPath email = createString("email");

    public final StringPath firstPriority = createString("firstPriority");

    public final NumberPath<Integer> grade = createNumber("grade", Integer.class);

    public final StringPath hopeField = createString("hopeField");

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final StringPath major = createString("major");

    public final StringPath minor = createString("minor");

    public final StringPath name = createString("name");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final StringPath secondPriority = createString("secondPriority");

    public final NumberPath<Integer> semester = createNumber("semester", Integer.class);

    public final NumberPath<Integer> studentId = createNumber("studentId", Integer.class);

    public final StringPath supportPath = createString("supportPath");

    // inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QApplicant(String variable) {
        super(Applicant.class, forVariable(variable));
    }

    public QApplicant(Path<? extends Applicant> path) {
        super(path.getType(), path.getMetadata());
    }

    public QApplicant(PathMetadata metadata) {
        super(Applicant.class, metadata);
    }
}
