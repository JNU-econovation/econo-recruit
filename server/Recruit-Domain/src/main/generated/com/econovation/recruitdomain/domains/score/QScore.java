package com.econovation.recruitdomain.domains.score;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;
import javax.annotation.processing.Generated;

/** QScore is a Querydsl query type for Score */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QScore extends EntityPathBase<Score> {

    private static final long serialVersionUID = -1795191327L;

    public static final QScore score1 = new QScore("score1");

    public final NumberPath<Applicant> applicant = createNumber("applicant", Applicant.class);

    public final StringPath criteria = createString("criteria");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> idpId = createNumber("idpId", Integer.class);

    public final NumberPath<Float> score = createNumber("score", Float.class);

    public QScore(String variable) {
        super(Score.class, forVariable(variable));
    }

    public QScore(Path<? extends Score> path) {
        super(path.getType(), path.getMetadata());
    }

    public QScore(PathMetadata metadata) {
        super(Score.class, metadata);
    }
}
