package com.econovation.recruitdomain.domains.label;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;
import javax.annotation.processing.Generated;

/** QLabel is a Querydsl query type for Label */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLabel extends EntityPathBase<Label> {

    private static final long serialVersionUID = 1042988325L;

    public static final QLabel label = new QLabel("label");

    public final NumberPath<Applicant> applicant = createNumber("applicant", Applicant.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> idpId = createNumber("idpId", Integer.class);

    public QLabel(String variable) {
        super(Label.class, forVariable(variable));
    }

    public QLabel(Path<? extends Label> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLabel(PathMetadata metadata) {
        super(Label.class, metadata);
    }
}
