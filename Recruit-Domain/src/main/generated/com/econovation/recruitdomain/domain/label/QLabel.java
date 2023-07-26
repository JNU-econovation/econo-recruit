package com.econovation.recruitdomain.domain.label;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLabel is a Querydsl query type for Label
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLabel extends EntityPathBase<Label> {

    private static final long serialVersionUID = -921726234L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLabel label = new QLabel("label");

    public final com.econovation.recruitdomain.domain.applicant.QApplicant applicant;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> idpId = createNumber("idpId", Integer.class);

    public QLabel(String variable) {
        this(Label.class, forVariable(variable), INITS);
    }

    public QLabel(Path<? extends Label> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLabel(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLabel(PathMetadata metadata, PathInits inits) {
        this(Label.class, metadata, inits);
    }

    public QLabel(Class<? extends Label> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.applicant = inits.isInitialized("applicant") ? new com.econovation.recruitdomain.domain.applicant.QApplicant(forProperty("applicant")) : null;
    }

}

